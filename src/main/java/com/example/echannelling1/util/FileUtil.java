package com.example.echannelling1.util;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class FileUtil {

    // Ensures the file and its parent directory exist
    public static void ensureFileExists(String path) throws IOException {
        File file = new File(path);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs(); // Create "data" folder if needed
        }
        if (!file.exists()) {
            file.createNewFile(); // Create the .txt file itself
        }
    }

    // Read all lines from file
    public static List<String> readLines(String path) throws IOException {
        ensureFileExists(path);
        return Files.readAllLines(Paths.get(path));
    }

    // Overwrite the file with new lines
    public static void writeLines(String path, List<String> lines) throws IOException {
        ensureFileExists(path);
        Files.write(Paths.get(path), lines);
    }

    // Append a single line to the file
    public static void appendLine(String path, String line) throws IOException {
        ensureFileExists(path);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(line);
            writer.newLine();
        }
    }
}
