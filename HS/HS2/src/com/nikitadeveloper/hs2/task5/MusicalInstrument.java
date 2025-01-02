package com.nikitadeveloper.hs2.task5;

public abstract class MusicalInstrument {
    private final String name;
    private final String description;

    protected MusicalInstrument(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void sound();

    public void show() {
        System.out.println("Назва інструмента: " + name);
    }

    public void desc() {
        System.out.println("Опис: " + description);
    }

    public abstract void history();
}