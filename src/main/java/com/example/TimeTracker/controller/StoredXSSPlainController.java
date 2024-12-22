package com.example.TimeTracker.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;


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
        // HTML-encode the comment to prevent XSS before storing it
        String sanitizedComment = HtmlUtils.htmlEscape(comment);  // This is a Spring utility that escapes HTML characters
        System.out.println(sanitizedComment);
        comments.add(sanitizedComment); // Store the sanitized comment

        //This si the insecure code
        //System.out.println(comment);
        //comments.add(comment); // Store the comment without sanitization (vulnerable)
    }
}
