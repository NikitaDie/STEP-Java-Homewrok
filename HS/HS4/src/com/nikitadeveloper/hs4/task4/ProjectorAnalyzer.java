package com.nikitadeveloper.hs4.task4;

import java.util.*;

public class ProjectorAnalyzer {
    public static void main(String[] args) {
        List<Projector> projectors = Arrays.asList(
                new Projector("Epson X500", 2025, 15000, "Epson"),
                new Projector("Sony VPL", 2022, 20000, "Sony"),
                new Projector("BenQ TK800", 2021, 18000, "BenQ"),
                new Projector("Optoma UHD50", 2023, 22000, "Optoma"),
                new Projector("LG CineBeam", 2020, 17000, "LG"),
                new Projector("Epson EH-TW7100", 2025, 25000, "Epson")
        );

        Scanner scanner = new Scanner(System.in);

        System.out.println("Всі проектори:");
        projectors.forEach(System.out::println);

        System.out.print("\nВведіть назву виробника для пошуку: ");
        String manufacturer = scanner.nextLine();
        System.out.println("Проектори виробника \"" + manufacturer + "\":");
        projectors.stream()
                .filter(projector -> projector.manufacturer().equalsIgnoreCase(manufacturer))
                .forEach(System.out::println);

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        System.out.println("\nПроектори поточного року (" + currentYear + "):");
        projectors.stream()
                .filter(projector -> projector.year() == currentYear)
                .forEach(System.out::println);

        System.out.print("\nВведіть мінімальну ціну для пошуку: ");
        int price = scanner.nextInt();
        System.out.println("Проектори дорожче " + price + ":");
        projectors.stream()
                .filter(projector -> projector.price() > price)
                .forEach(System.out::println);

        System.out.println("\nПроектори, відсортовані за ціною (зростання):");
        projectors.stream()
                .sorted(Comparator.comparingInt(Projector::price))
                .forEach(System.out::println);

        System.out.println("\nПроектори, відсортовані за ціною (спадання):");
        projectors.stream()
                .sorted(Comparator.comparingInt(Projector::price).reversed())
                .forEach(System.out::println);

        System.out.println("\nПроектори, відсортовані за роком випуску (зростання):");
        projectors.stream()
                .sorted(Comparator.comparingInt(Projector::year))
                .forEach(System.out::println);

        System.out.println("\nПроектори, відсортовані за роком випуску (спадання):");
        projectors.stream()
                .sorted(Comparator.comparingInt(Projector::year).reversed())
                .forEach(System.out::println);
    }
}
