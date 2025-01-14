package com.nikitadeveloper.hs4.task3;

public record Device(String name, int year, int price, String color, String type) {

    @Override
    public String toString() {
        return "Пристрій{" +
                "назва='" + name + '\'' +
                ", рік=" + year +
                ", ціна=" + price +
                ", колір='" + color + '\'' +
                ", тип='" + type + '\'' +
                '}';
    }
}
