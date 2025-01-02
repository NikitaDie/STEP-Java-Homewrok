package com.nikitadeveloper.hs2.task4;

public class Microwave extends Device {
    public Microwave(String description) {
        super("Мікрохвильовка", description);
    }

    @Override
    public void sound() {
        System.out.println("Звук мікрохвильовки: Піп-піп!");
    }
}