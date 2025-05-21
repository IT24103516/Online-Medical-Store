package com.example.echannelling1.Service;

import com.example.echannelling1.Classes.User;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final List<User> userList = new ArrayList<>();
    private final String FILE_PATH = "users.txt";

    public UserService() {
        loadFromFile();
    }

    // Register a new user
    public boolean registerUser(User user) {
        // Check if username already exists
        if (userList.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()))) {
            return false;
        }

        // Hash the password before storing
        user.setPassword(hashPassword(user.getPassword()));
        
        // Add user to list and save to file
        userList.add(user);
        saveToFile();
        return true;
    }

    // Authenticate a user
    public Optional<User> authenticateUser(String username, String password) {
        String hashedPassword = hashPassword(password);
        
        return userList.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(hashedPassword))
                .findFirst();
    }

    // Get user by username
    public Optional<User> getUserByUsername(String username) {
        return userList.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    // Get all users
    public List<User> getAllUsers() {
        return userList;
    }

    // Update user information
    public boolean updateUser(String username, User updatedUser) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(username)) {
                // If password is changed, hash it
                if (!updatedUser.getPassword().equals(userList.get(i).getPassword())) {
                    updatedUser.setPassword(hashPassword(updatedUser.getPassword()));
                }
                
                userList.set(i, updatedUser);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    // Delete a user
    public boolean deleteUser(String username) {
        boolean removed = userList.removeIf(u -> u.getUsername().equals(username));
        if (removed) saveToFile();
        return removed;
    }

    // Hash password using SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            
            // Convert byte array to hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Fallback to plain text if hashing fails
            return password;
        }
    }

    // Save users to file
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : userList) {
                writer.write(user.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load users from file
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
                    User user = User.fromFileString(line);
                    userList.add(user);
                } catch (Exception e) {
                    System.err.println("Error processing user line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
