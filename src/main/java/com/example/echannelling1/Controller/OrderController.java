package com.example.echannelling1.Controller;

import com.example.echannelling1.Service.OrderService;
import com.example.echannelling1.model.Order;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service = new OrderService();

    @PostMapping("/add")
    public Map<String, String> addOrder(@RequestBody Order order) {
        service.addOrder(order);
        return Map.of("message", "Order added successfully");
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return service.getAllOrders();
    }

    @PutMapping("/update/{id}")
    public Map<String, String> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        boolean success = service.updateOrder(id, order);
        return Map.of("message", success ? "Order updated successfully" : "Order not found");
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, String> deleteOrder(@PathVariable Long id) {
        boolean success = service.deleteOrder(id);
        return Map.of("message", success ? "Order deleted successfully" : "Order not found");
    }
}
