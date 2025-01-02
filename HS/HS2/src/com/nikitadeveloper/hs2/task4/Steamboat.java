package com.nikitadeveloper.hs2.task4;

public class Steamboat extends Device {
    public Steamboat(String description) {
        super("Пароплав", description);
    }

    @Override
    public void sound() {
        System.out.println("Звук пароплава: Ту-тууу!");
    }
}