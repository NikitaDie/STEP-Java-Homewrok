package com.nikitadeveloper.hs2.task5;

public class Ukulele extends MusicalInstrument {
    private static final String NAME = "Ukulele";

    public Ukulele(String description) {
        super(NAME, description);
    }

    @Override
    public void sound() {
        System.out.println("Звук укулеле: Тінь-тань-тінь!");
    }

    @Override
    public void history() {
        System.out.println("Історія: Укулеле походить з Гавайських островів і виникло у XIX столітті.");
    }
}