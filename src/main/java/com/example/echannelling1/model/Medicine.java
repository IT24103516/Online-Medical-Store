package com.example.echannelling1.model;

public class Medicine {
    private String id;
    private String name;
    private double price;
    private String imageName;

    public Medicine() {}

    public Medicine(String id, String name, double price, String imageName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageName = imageName;
    }

    public static Medicine fromString(String line) {
        String[] parts = line.split("\\|");
        return new Medicine(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]);
    }

    public String toFileString() {
        return id + "|" + name + "|" + price + "|" + imageName;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImageName() { return imageName; }
    public void setImageName(String imageName) { this.imageName = imageName; }
}
