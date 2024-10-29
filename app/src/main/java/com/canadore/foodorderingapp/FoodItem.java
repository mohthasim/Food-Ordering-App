package com.canadore.foodorderingapp;

public class FoodItem {
    private String id;
    private String name;
    private double price;
    private String description;
    private String imageURL;

    public FoodItem() {
        // Default constructor required for calls to DataSnapshot.getValue(FoodItem.class)
    }

    public FoodItem(String id, String name, double price, String description, String imageURL) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageURL = imageURL;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageURL() { return imageURL; }
    public void setImageURL(String imageURL) { this.imageURL = imageURL; }
}

