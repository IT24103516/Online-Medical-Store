package com.example.echannelling1.model;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private String role;

    public User() {}

    public User(String id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static User fromString(String line) {
        String[] parts = line.split("\\|");
        return new User(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }

    public String toFileString() {
        return id + "|" + name + "|" + email + "|" + password + "|" + role;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
