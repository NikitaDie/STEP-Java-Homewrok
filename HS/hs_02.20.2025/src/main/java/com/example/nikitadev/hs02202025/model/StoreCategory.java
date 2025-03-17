package com.example.nikitadev.hs02202025.model;

public enum StoreCategory {
    GROCERY("Продовольчий"),
    HOUSEHOLD("Господарський"),
    SPORTS("Спортивний"),
    ELECTRONICS("Електроніка"),
    CLOTHING("Одяг");

    private final String displayName;

    StoreCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}