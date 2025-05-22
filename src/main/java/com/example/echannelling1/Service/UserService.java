package com.example.echannelling1.Service;

import com.example.echannelling1.model.User;
import com.example.echannelling1.util.FileUtil;

import java.io.IOException;
import java.util.List;

public class UserService {

    private static final String FILE_PATH = "data/users.txt";

    public void registerUser(User user) throws IOException {
        FileUtil.appendLine(FILE_PATH, user.toFileString());
    }

    public User loginUser(String email, String password) throws IOException {
        List<String> lines = FileUtil.readLines(FILE_PATH);
        for (String line : lines) {
            User user = User.fromString(line);
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public boolean emailExists(String email) throws IOException {
        List<String> lines = FileUtil.readLines(FILE_PATH);
        for (String line : lines) {
            User user = User.fromString(line);
            if (user.getEmail().equalsIgnoreCase(email)) return true;
        }
        return false;
    }
}
