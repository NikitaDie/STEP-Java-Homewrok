package com.nikitadeveloper.hs2.task3;

public class Product {
    private String name;
    private Money price;

    public Product(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public void reducePrice(Money amount) {
        System.out.print("Old product's price: ");
        System.out.println(this.price);

        price.subtract(amount);

        System.out.print("New product's price: ");
        System.out.println(this.price);
    }

    public void increasePrice(Money amount) {
        System.out.print("Old product's price: ");
        System.out.println(this.price);

        price.add(amount);

        System.out.print("New product's price: ");
        System.out.println(this.price);
    }
}
