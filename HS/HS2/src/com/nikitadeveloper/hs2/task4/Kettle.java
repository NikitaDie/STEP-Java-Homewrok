package com.nikitadeveloper.hs2.task4;

public class Kettle extends Device {
    public Kettle(String description) {
        super("Чайник", description);
    }

    @Override
    public void sound() {
        System.out.println("Звук чайника: Ш-ш-ш!");
    }
}