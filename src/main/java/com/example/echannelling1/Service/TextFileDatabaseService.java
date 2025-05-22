package com.example.echannelling1.Service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TextFileDatabaseService {
    private static final String DB_FILE = "orders_db.txt";
    private static final Path DB_PATH = Paths.get(DB_FILE);

    public void saveOrder(Order order) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB_FILE, true))) {
            writer.write(order.toString());
            writer.newLine();
        }
    }

    public List<Order> getAllOrders() throws IOException {
        if (!Files.exists(DB_PATH)) {
            return new ArrayList<>();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE))) {
            return reader.lines()
                    .map(Order::fromString)
                    .collect(Collectors.toList());
        }
    }

    public Order getOrderById(Long id) throws IOException {
        return getAllOrders().stream()
                .filter(order -> order.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteOrder(Long id) throws IOException {
        List<Order> orders = getAllOrders().stream()
                .filter(order -> !order.getId().equals(id))
                .collect(Collectors.toList());

        // Rewrite the entire file without the deleted order
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB_FILE))) {
            for (Order order : orders) {
                writer.write(order.toString());
                writer.newLine();
            }
        }
    }
}