package com.example.echannelling1.Service;

import com.example.echannelling1.model.Order;

import java.util.LinkedList;
import java.util.List;

public class Queue {

    private final LinkedList<Order> orderQueue;

    public Queue() {
        orderQueue = new LinkedList<>();
    }

    public void enqueue(Order order) {
        orderQueue.addLast(order);
    }

    public Order dequeue() {
        return orderQueue.pollFirst();
    }

    public boolean isEmpty() {
        return orderQueue.isEmpty();
    }

    public List<Order> getAllOrders() {
        return new LinkedList<>(orderQueue);
    }

    public int size() {
        return orderQueue.size();
    }

    public void clear() {
        orderQueue.clear();
    }
}
