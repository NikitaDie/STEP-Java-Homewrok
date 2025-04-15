package com.example.nikitadeveloper.hs01032025.model;

public enum MealCategory {
    VEGETARIAN("Vegetarian"),
    NON_VEGETARIAN("Non-Vegetarian"),
    VEGAN("Vegan"),
    GLUTEN_FREE("Gluten-Free"),
    DAIRY_FREE("Dairy-Free"),
    KETO("Keto"),
    PALEO("Paleo"),
    MEDITERRANEAN("Mediterranean");

    private final String displayName;

    MealCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
