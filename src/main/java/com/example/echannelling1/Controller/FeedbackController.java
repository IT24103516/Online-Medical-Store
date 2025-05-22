package com.example.echannelling1.Controller;

import com.example.echannelling1.Classes.Feedback;
import com.example.echannelling1.Service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService = new FeedbackService();

    // Add new feedback
    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addFeedback(@RequestBody Feedback feedback) {
        Map<String, String> response = new HashMap<>();
        
        // Validate input
        if (feedback.getMessage() == null || feedback.getMessage().trim().isEmpty()) {
            response.put("message", "Feedback message is required");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        
        feedbackService.addFeedback(feedback);
        response.put("message", "Feedback submitted successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all feedback
    @GetMapping("/all")
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        List<Feedback> feedbackList = feedbackService.getAllFeedback();
        return ResponseEntity.ok(feedbackList);
    }

    // Get feedback by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getFeedbackById(@PathVariable String id) {
        Optional<Feedback> feedback = feedbackService.getFeedbackById(id);
        
        if (feedback.isPresent()) {
            return ResponseEntity.ok(feedback.get());
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Feedback not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Get feedback by username
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Feedback>> getFeedbackByUsername(@PathVariable String username) {
        List<Feedback> feedbackList = feedbackService.getFeedbackByUsername(username);
        return ResponseEntity.ok(feedbackList);
    }

    // Update feedback
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, String>> updateFeedback(@PathVariable String id, @RequestBody Feedback updatedFeedback) {
        Map<String, String> response = new HashMap<>();
        
        boolean success = feedbackService.updateFeedback(id, updatedFeedback);
        
        if (success) {
            response.put("message", "Feedback updated successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Feedback not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Delete feedback
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteFeedback(@PathVariable String id) {
        Map<String, String> response = new HashMap<>();
        
        boolean success = feedbackService.deleteFeedback(id);
        
        if (success) {
            response.put("message", "Feedback deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Feedback not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Update feedback status
    @PutMapping("/status/{id}")
    public ResponseEntity<Map<String, String>> updateFeedbackStatus(@PathVariable String id, @RequestBody Map<String, String> statusUpdate) {
        Map<String, String> response = new HashMap<>();
        
        String status = statusUpdate.get("status");
        if (status == null || status.trim().isEmpty()) {
            response.put("message", "Status is required");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        
        boolean success = feedbackService.updateFeedbackStatus(id, status);
        
        if (success) {
            response.put("message", "Feedback status updated successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Feedback not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
