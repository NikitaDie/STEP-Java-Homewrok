package com.nikitadeveloper.hs2.task2;

public class Crocodile extends Animal {
    private double length;

    public Crocodile(String name, int age, double length) {
        super(name, age);
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Crocodile{" +
            "name='" + getName() + '\'' +
            ", age=" + getAge() +
            ", length=" + length +
            '}';
    }
}
