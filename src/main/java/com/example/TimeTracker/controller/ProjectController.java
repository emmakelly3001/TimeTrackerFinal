package com.example.TimeTracker.controller;

import com.example.TimeTracker.entity.User;
import com.example.TimeTracker.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProjectController {

    @Autowired
    private final JdbcTemplate jdbcTemplate;



    // Constructor injection for JdbcTemplate
    public ProjectController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/searchProject")
    public Map<String, Object> searchProject(@RequestBody Map<String, String> payload) {
        String name = payload.get("name");
        String query = "SELECT * FROM projects WHERE name = '" + name + "'"; // **Vulnerable to SQL Injection**

        Map<String, Object> response = new HashMap<>();

        // This query is vulnerable because the input is directly inserted into the query without sanitization
        try {
            var result = jdbcTemplate.queryForList(query);
            if (result.isEmpty()) {
                response.put("status", "error");
                response.put("message", "Project not found.");
            }
            else {
                response.put("status", "success");
                response.put("message", "Project found.");
                response.put("data", result); // Add the query result to the response
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error: " + e.getMessage());
        }

        return response;

    }
}
