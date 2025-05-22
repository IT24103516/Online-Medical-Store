package com.example.echannelling1.Classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Feedback {
    private String id;
    private String username;
    private String userEmail;
    private String subject;
    private String message;
    private int rating;
    private String dateTime;
    private String status; // "pending", "reviewed", "resolved"

    public Feedback() {
        this.dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.status = "pending";
    }

    public Feedback(String id, String username, String userEmail, String subject, String message, int rating, String dateTime, String status) {
        this.id = id;
        this.username = username;
        this.userEmail = userEmail;
        this.subject = subject;
        this.message = message;
        this.rating = rating;
        this.dateTime = dateTime;
        this.status = status;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Method to convert Feedback object to a string for file storage
    public String toFileString() {
        return String.join(",", id, username, userEmail, subject, message, String.valueOf(rating), dateTime, status);
    }

    // Method to create Feedback object from a string from file
    public static Feedback fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 8) {
            throw new IllegalArgumentException("Invalid feedback record: " + line);
        }
        return new Feedback(
                parts[0], // id
                parts[1], // username
                parts[2], // userEmail
                parts[3], // subject
                parts[4], // message
                Integer.parseInt(parts[5]), // rating
                parts[6], // dateTime
                parts[7]  // status
        );
    }
}
