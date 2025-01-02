package com.nikitadeveloper.hs2.task5;

public class Cello extends MusicalInstrument {
    private static final String NAME = "Віолончель";

    public Cello(String description) {
        super(NAME, description);
    }

    @Override
    public void sound() {
        System.out.println("Звук віолончелі: Ду-ду-дууу!");
    }

    @Override
    public void history() {
        System.out.println("Історія: Віолончель виникла у XVI столітті і стала популярною в класичній музиці.");
    }
}