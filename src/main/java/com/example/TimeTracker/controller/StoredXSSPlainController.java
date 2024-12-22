package com.example.TimeTracker.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class StoredXSSPlainController {
    private final List<String> comments = new ArrayList<>();

    // Endpoint to get all comments
    @GetMapping
    public List<String> getComments() {
        return comments; // Return comments as JSON
    }

    // Endpoint to add a new comment
    @PostMapping
    public void addComment(@RequestParam("comment") String comment) {
        System.out.println(comment);
        comments.add(comment); // Store the comment without sanitization (vulnerable)
    }
}
