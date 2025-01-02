package com.nikitadeveloper.hs2.task2;

public class Kangaroo extends Animal {
    private int jumpHeight;

    public Kangaroo(String name, int age, int jumpHeight) {
        super(name, age);
        this.jumpHeight = jumpHeight;
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    @Override
    public String toString() {
        return "Kangaroo{" +
            "name='" + getName() + '\'' +
            ", age=" + getAge() +
            ", jumpHeight=" + jumpHeight +
            '}';
    }
}
