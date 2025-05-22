package com.example.echannelling1.Service;

import com.example.echannelling1.Classes.Feedback;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FeedbackService {
    private final List<Feedback> feedbackList = new ArrayList<>();
    private final String FILE_PATH = "feedback.txt";

    public FeedbackService() {
        loadFromFile();
    }

    // Add new feedback
    public void addFeedback(Feedback feedback) {
        // Generate a unique ID if not provided
        if (feedback.getId() == null || feedback.getId().isEmpty()) {
            feedback.setId("FB" + System.currentTimeMillis());
        }
        
        // Set current date and time if not provided
        if (feedback.getDateTime() == null || feedback.getDateTime().isEmpty()) {
            feedback.setDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        
        // Set default status if not provided
        if (feedback.getStatus() == null || feedback.getStatus().isEmpty()) {
            feedback.setStatus("pending");
        }
        
        feedbackList.add(feedback);
        saveToFile();
    }

    // Get all feedback
    public List<Feedback> getAllFeedback() {
        return feedbackList;
    }

    // Get feedback by ID
    public Optional<Feedback> getFeedbackById(String id) {
        return feedbackList.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst();
    }

    // Get feedback by username
    public List<Feedback> getFeedbackByUsername(String username) {
        List<Feedback> userFeedback = new ArrayList<>();
        for (Feedback feedback : feedbackList) {
            if (feedback.getUsername().equals(username)) {
                userFeedback.add(feedback);
            }
        }
        return userFeedback;
    }

    // Update feedback
    public boolean updateFeedback(String id, Feedback updatedFeedback) {
        for (int i = 0; i < feedbackList.size(); i++) {
            if (feedbackList.get(i).getId().equals(id)) {
                // Preserve the original ID
                updatedFeedback.setId(id);
                feedbackList.set(i, updatedFeedback);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    // Delete feedback
    public boolean deleteFeedback(String id) {
        boolean removed = feedbackList.removeIf(f -> f.getId().equals(id));
        if (removed) saveToFile();
        return removed;
    }

    // Update feedback status
    public boolean updateFeedbackStatus(String id, String status) {
        for (Feedback feedback : feedbackList) {
            if (feedback.getId().equals(id)) {
                feedback.setStatus(status);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    // Save feedback to file
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Feedback feedback : feedbackList) {
                writer.write(feedback.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load feedback from file
    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Feedback feedback = Feedback.fromFileString(line);
                    feedbackList.add(feedback);
                } catch (Exception e) {
                    System.err.println("Error processing feedback line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
