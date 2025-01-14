package com.nikitadeveloper.hs4.task4;

public record Projector(String name, int year, int price, String manufacturer) {

    @Override
    public String toString() {
        return "Проектор{" +
                "назва='" + name + '\'' +
                ", рік=" + year +
                ", ціна=" + price +
                ", виробник='" + manufacturer + '\'' +
                '}';
    }
}
