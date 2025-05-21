package com.example.echannelling1.Classes;

public class User {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String role;
    private String phoneNumber;

    public User() {
    }

    public User(String username, String password, String email, String fullName, String role, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Method to convert User object to a string for file storage
    public String toFileString() {
        return String.join(",", username, password, email, fullName, role, phoneNumber);
    }

    // Method to create User object from a string from file
    public static User fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Invalid user record: " + line);
        }
        return new User(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
    }
}
