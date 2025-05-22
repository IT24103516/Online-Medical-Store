package com.example.echannelling1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Order {
    private String orderId;
    private String userId;
    private String userName;
    private List<CartItem> items;
    private double totalPrice;

    public Order(String userId, String userName, List<CartItem> items) {
        this.orderId = UUID.randomUUID().toString();
        this.userId = userId;
        this.userName = userName;
        this.items = items;
        this.totalPrice = items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public static Order fromString(String line) {
        try {
            String[] parts = line.split("\\|");
            String orderId = parts[0];
            String userId = parts[1];
            String userName = parts[2];
            double total = Double.parseDouble(parts[3]);

            List<CartItem> items = new ArrayList<>();
            if (parts.length > 4 && parts[4] != null && !parts[4].isBlank()) {
                String[] itemParts = parts[4].split(";");
                for (String item : itemParts) {
                    if (!item.isBlank()) {
                        items.add(CartItem.fromString(item));
                    }
                }
            }

            Order order = new Order(userId, userName, items);
            order.orderId = orderId;
            order.totalPrice = total;
            return order;
        } catch (Exception e) {
            System.err.println("Error parsing order line: " + line);
            e.printStackTrace();
            return null;
        }
    }

    public String toFileString() {
        StringBuilder itemData = new StringBuilder();
        for (CartItem item : items) {
            itemData.append(item.toFileString()).append(";");
        }
        return orderId + "|" + userId + "|" + userName + "|" + totalPrice + "|" + itemData;
    }

    // Getters
    public String getOrderId() { return orderId; }
    public String getUserId() { return userId; }
    public String getUserName() { return userName; }
    public List<CartItem> getItems() { return items; }
    public double getTotalPrice() { return totalPrice; }
}
