package com.example.echannelling1.Controller;

import com.example.echannelling1.model.User;
import com.example.echannelling1.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@Controller
public class AuthController {

    private final UserService userService = new UserService();

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) throws IOException {
        if (userService.emailExists(user.getEmail())) {
            model.addAttribute("error", "Email already registered!");
            return "register";
        }

        user.setId(UUID.randomUUID().toString());
        userService.registerUser(user);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) throws IOException {
        User user = userService.loginUser(email, password);
        if (user == null) {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }

        session.setAttribute("loggedInUser", user);
        return user.getRole().equals("admin") ?
                "redirect:/admin/medicines" : "redirect:/user/medicines";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
