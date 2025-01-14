package com.nikitadeveloper.hs4.task2;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProductAnalyzer {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Молоко", "Молоко"),
                new Product("Сир", "Молоко"),
                new Product("Хліб", "Випічка"),
                new Product("Яблуко", "Фрукти"),
                new Product("Масло", "Молоко"),
                new Product("Кефір", "Молоко"),
                new Product("Торт", "Випічка"),
                new Product("Сік", "Напої"),
                new Product("Сир", "Молоко")
        );

        Scanner scanner = new Scanner(System.in);

        System.out.println("Всі продукти:");
        products.forEach(System.out::println);

        System.out.println("\nПродукти з назвою менше п’яти символів:");
        products.stream()
                .filter(product -> product.name().length() < 5)
                .forEach(System.out::println);

        System.out.print("\nВведіть назву продукту для підрахунку: ");
        String productName = scanner.nextLine();
        long count = products.stream()
                .filter(product -> product.name().equalsIgnoreCase(productName))
                .count();
        System.out.println("Продукт \"" + productName + "\" зустрічається " + count + " разів.");

        System.out.print("\nВведіть букву для пошуку продуктів: ");
        char startLetter = scanner.nextLine().toLowerCase().charAt(0);
        System.out.println("Продукти, що починаються на букву \"" + startLetter + "\":");
        products.stream()
                .filter(product -> product.name().toLowerCase().charAt(0) == startLetter)
                .forEach(System.out::println);

        System.out.println("\nПродукти із категорії «Молоко»:");
        products.stream()
                .filter(product -> product.category().equalsIgnoreCase("Молоко"))
                .forEach(System.out::println);
    }
}
