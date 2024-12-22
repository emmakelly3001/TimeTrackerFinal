package com.example.TimeTracker.controller;

import com.example.TimeTracker.entity.User;
import com.example.TimeTracker.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProjectController {

    @Autowired
    private final JdbcTemplate jdbcTemplate;


    // Constructor injection for JdbcTemplate
    public ProjectController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //SQL Injection
    @PostMapping("/searchProject")
    public Map<String, Object> searchProject(@RequestBody Map<String, String> payload) {
        String name = payload.get("name");

        //Using parameterized query to prevent SQL injection
        String query = "SELECT * FROM projects WHERE name = ?";

        //Vulnerable to SQL Injection
        //String query = "SELECT * FROM projects WHERE name = '" + name + "'";

        Map<String, Object> response = new HashMap<>();

        // This query is vulnerable because the input is directly inserted into the query without sanitization
        try {

            //Execute the query with parameters
            var result = jdbcTemplate.queryForList(query, name);

            //Vulnerable to SQL Injection
            // var result = jdbcTemplate.queryForList(query);

            if (result.isEmpty()) {
                response.put("status", "error");
                response.put("message", "Project not found.");
            } else {
                response.put("status", "success");
                response.put("message", "Project found.");
                response.put("data", result);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error: " + e.getMessage());
        }

        return response;

    }

    //controller for Reflected XSS
    @GetMapping("/echo")
    public String reflectUserInput(@RequestParam("input") String userInput) {
        //HTML encoding to prevent XSS
        String sanitizedInput = userInput.replaceAll("&", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot;")
                .replaceAll("'", "&#x27;");
        return "<html><body>You entered: " + sanitizedInput + "</body></html>";

        // Directly returning user input without sanitization
        //return "<html><body>You entered: " + userInput + "</body></html>";
    }
}