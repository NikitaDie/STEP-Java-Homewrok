package com.nikitadeveloper.hs3.task3;

public class Fine {
    private final String id;
    private FineType type;
    private double amount;
    private String city;
    private String description;

    public Fine(String id, FineType type, double amount, String city, String description) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.city = city;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setType(FineType type) {
        this.type = type;
    }

    public FineType getType() {
        return type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Type: " + type + ", Amount: " + amount + ", City: " + city + ", Description: " + description;
    }
}
