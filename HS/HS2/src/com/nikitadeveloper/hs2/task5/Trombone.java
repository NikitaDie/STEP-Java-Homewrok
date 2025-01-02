package com.nikitadeveloper.hs2.task5;

public class Trombone extends MusicalInstrument {
    private static final String NAME = "Тромбон";

    public Trombone(String description) {
        super(NAME, description);
    }

    @Override
    public void sound() {
        System.out.println("Звук тромбона: Пум-пум-пум!");
    }

    @Override
    public void history() {
        System.out.println("Історія: Тромбон виник у XV столітті як розширення труби.");
    }
}
