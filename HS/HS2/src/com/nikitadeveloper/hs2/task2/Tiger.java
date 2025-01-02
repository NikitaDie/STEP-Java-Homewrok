package com.nikitadeveloper.hs2.task2;

public class Tiger extends Animal {
    private String stripeColor;

    public Tiger(String name, int age, String stripeColor) {
        super(name, age);
        this.stripeColor = stripeColor;
    }

    public String getStripeColor() {
        return stripeColor;
    }

    public void setStripeColor(String stripeColor) {
        this.stripeColor = stripeColor;
    }

    @Override
    public String toString() {
        return "Tiger{" +
            "name='" + getName() + '\'' +
            ", age=" + getAge() +
            ", stripeColor='" + stripeColor + '\'' +
            '}';
    }
}
