package com.example.echannelling1.model;



public class CartItem {
    private String medicineId;
    private String medicineName;
    private double unitPrice;
    private int quantity;
    private double totalPrice;

    public CartItem(String medicineId, String medicineName, double unitPrice, int quantity) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = unitPrice * quantity;
    }

    public static CartItem fromString(String line) {
        String[] parts = line.split(",");
        return new CartItem(
                parts[0],
                parts[1],
                Double.parseDouble(parts[2]),
                Integer.parseInt(parts[3])
        );
    }

    public String toFileString() {
        return medicineId + "," + medicineName + "," + unitPrice + "," + quantity;
    }

    // Getters
    public String getMedicineName() { return medicineName; }
    public double getUnitPrice() { return unitPrice; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return totalPrice; }
}
