package com.nikitadeveloper.hs2.task5;

public class Violin extends MusicalInstrument {
    private static final String NAME = "Скрипка";

    public Violin(String description) {
        super(NAME, description);
    }

    @Override
    public void sound() {
        System.out.println("Звук скрипки: Ля-ля-ля!");
    }

    @Override
    public void history() {
        System.out.println("Історія: Скрипка з'явилася у XVI столітті в Італії.");
    }
}
