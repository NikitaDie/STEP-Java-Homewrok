package com.nikitadeveloper.hs2.task4;

public abstract class Device {
    private final String name;
    private final String description;

    protected Device(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void sound();

    public void show() {
        System.out.println("Назва пристрою: " + name);
    }

    public void desc() {
        System.out.println("Опис: " + description);
    }
}
