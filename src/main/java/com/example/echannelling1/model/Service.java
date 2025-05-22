package com.oop.online.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    private String id;
    private String name;
    private String description;
    private String category;
    private String contact;
    private String location;
    private String price;
    private String imageName;

    public static Service fromString(String line) {
        String[] parts = line.split("\\|");
        return new Service(
                parts[0], parts[1], parts[2], parts[3],
                parts[4], parts[5], parts[6], parts[7]
        );
    }

    public String toFileString() {
        return String.join("|", id, name, description, category, contact, location, price, imageName);
    }
}
