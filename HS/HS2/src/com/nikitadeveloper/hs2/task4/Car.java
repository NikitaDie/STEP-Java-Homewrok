package com.nikitadeveloper.hs2.task4;

public class Car extends Device {
    public Car(String description) {
        super("Автомобіль", description);
    }

    @Override
    public void sound() {
        System.out.println("Звук автомобіля: Врум-врум!");
    }
}
