package com.example.echannelling1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Echannelling1Application {

    public static void main(String[] args) {
        SpringApplication.run(Echannelling1Application.class, args);

        // Optional: Auto open browser
        String url = "http://localhost:8080";
        try {
            if (java.awt.Desktop.isDesktopSupported()) {
                java.awt.Desktop.getDesktop().browse(new java.net.URI(url));
                System.out.println("Opened browser to: " + url);
            } else {
                System.out.println("Desktop is not supported. Please open: " + url);
            }
        } catch (Exception e) {
            System.out.println("Unable to open browser: " + e.getMessage());
        }
    }
}
