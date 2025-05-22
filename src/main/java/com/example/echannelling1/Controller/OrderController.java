package com.example.echannelling1.Controller;

import com.example.echannelling1.model.*;
import com.example.echannelling1.Service.MedicineService;
import com.example.echannelling1.Service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;


@Controller
@RequestMapping("/user")
public class OrderController {

    private final MedicineService medicineService = new MedicineService();
    private final OrderService orderService = new OrderService();

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();
        double total = cart.stream().mapToDouble(CartItem::getTotalPrice).sum();
        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        return "cart";
    }

    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable String id, @RequestParam int quantity, HttpSession session) throws IOException {
        Medicine med = medicineService.getMedicineById(id);
        if (med == null) return "redirect:/user/medicines";

        CartItem item = new CartItem(med.getId(), med.getName(), med.getPrice(), quantity);
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();
        cart.add(item);
        session.setAttribute("cart", cart);
        return "redirect:/user/medicines";
    }

    @GetMapping("/cart/remove/{index}")
    public String removeFromCart(@PathVariable int index, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart != null && index >= 0 && index < cart.size()) {
            cart.remove(index);
        }
        return "redirect:/user/cart";
    }

    @PostMapping("/cart/checkout")
    public String checkout(HttpSession session, Model model) throws IOException {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            model.addAttribute("error", "Cart is empty!");
            return "cart";
        }

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            model.addAttribute("error", "Please login first.");
            return "login";
        }

        Order order = new Order(user.getId(), user.getName(), cart);
        orderService.saveOrder(order);
        session.removeAttribute("cart");
        model.addAttribute("message", "Order placed successfully!");
        return "redirect:/user/orders";
    }

    @GetMapping("/orders")
    public String viewOrders(HttpSession session, Model model) throws IOException {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            model.addAttribute("error", "Please login first.");
            return "login";
        }
        List<Order> orders = orderService.getOrdersByUser(user.getId());
        model.addAttribute("orders", orders);
        return "order-history";
    }
}
