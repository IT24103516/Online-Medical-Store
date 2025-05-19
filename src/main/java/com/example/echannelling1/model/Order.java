package com.example.echannelling1.model;

import java.time.LocalDateTime;

public class Order {
    private Long id;
    private String customerName;
    private String medicineName;
    private int quantity;
    private double price;
    private LocalDateTime orderDate;

    // Constructors
    public Order() {
        this.orderDate = LocalDateTime.now();
    }

    public Order(Long id, String customerName, String medicineName, int quantity, double price) {
        this.id = id;
        this.customerName = customerName;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.price = price;
        this.orderDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    // Serialization methods
    @Override
    public String toString() {
        return id + "," +
                customerName + "," +
                medicineName + "," +
                quantity + "," +
                price + "," +
                orderDate;
    }

    public static Order fromString(String str) {
        String[] parts = str.split(",");
        Order order = new Order();
        order.setId(Long.parseLong(parts[0]));
        order.setCustomerName(parts[1]);
        order.setMedicineName(parts[2]);
        order.setQuantity(Integer.parseInt(parts[3]));
        order.setPrice(Double.parseDouble(parts[4]));
        order.setOrderDate(LocalDateTime.parse(parts[5]));
        return order;
    }

    // Optional: equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}