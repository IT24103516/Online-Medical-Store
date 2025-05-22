package com.example.echannelling1.Service;

import com.example.echannelling1.model.Order;
import com.example.echannelling1.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private static final String FILE_PATH = "data/orders.txt";

    public void saveOrder(Order order) throws IOException {
        FileUtil.appendLine(FILE_PATH, order.toFileString());
    }

    public List<Order> getAllOrders() throws IOException {
        FileUtil.ensureFileExists(FILE_PATH);
        List<String> lines = FileUtil.readLines(FILE_PATH);
        List<Order> orders = new ArrayList<>();
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                orders.add(Order.fromString(line));
            }
        }
        return orders;
    }

    public List<Order> getOrdersByUser(String userId) throws IOException {
        List<Order> all = getAllOrders();
        List<Order> filtered = new ArrayList<>();
        for (Order o : all) {
            if (o.getUserId().equals(userId)) {
                filtered.add(o);
            }
        }
        return filtered;
    }
}
