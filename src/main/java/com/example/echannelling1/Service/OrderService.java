package com.example.echannelling1.Service;

import com.example.echannelling1.model.Order;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class OrderService {
    private final String FILE_PATH = "orders.txt";
    private final List<Order> orderList = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public OrderService() {
        loadFromFile();
    }

    public List<Order> getAllOrders() {
        return orderList;
    }

    public void addOrder(Order order) {
        order.setId(idGenerator.getAndIncrement());
        orderList.add(order);
        saveToFile();
    }

    public boolean updateOrder(Long id, Order updatedOrder) {
        for (int i = 0; i < orderList.size(); i++) {
            if (Objects.equals(orderList.get(i).getId(), id)) {
                updatedOrder.setId(id);
                orderList.set(i, updatedOrder);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteOrder(Long id) {
        boolean removed = orderList.removeIf(order -> Objects.equals(order.getId(), id));
        if (removed) saveToFile();
        return removed;
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Order order : orderList) {
                writer.write(order.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Order order = Order.fromString(line);
                    orderList.add(order);
                    idGenerator.set(Math.max(idGenerator.get(), order.getId() + 1));
                } catch (Exception e) {
                    System.err.println("Invalid order line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
