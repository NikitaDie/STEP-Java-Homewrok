package com.nikitadeveloper.hs4.task2;

public record Product(String name, String category) {

    @Override
    public String toString() {
        return name;
    }
}
