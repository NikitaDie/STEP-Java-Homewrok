package com.nikitadeveloper.hs4.task3;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DeviceAnalyzer {
    public static void main(String[] args) {
        List<Device> devices = Arrays.asList(
                new Device("Ноутбук", 2021, 25000, "Чорний", "Електроніка"),
                new Device("Смартфон", 2020, 15000, "Срібний", "Електроніка"),
                new Device("Телевізор", 2019, 30000, "Чорний", "Електроніка"),
                new Device("Холодильник", 2018, 20000, "Білий", "Побутова техніка"),
                new Device("Пилосос", 2022, 12000, "Червоний", "Побутова техніка"),
                new Device("Планшет", 2021, 18000, "Сірий", "Електроніка"),
                new Device("Мікрохвильовка", 2017, 8000, "Білий", "Побутова техніка")
        );

        Scanner scanner = new Scanner(System.in);

        System.out.println("Всі пристрої:");
        devices.forEach(System.out::println);

        System.out.print("\nВведіть колір для пошуку: ");
        String color = scanner.nextLine();
        System.out.println("Пристрої кольору \"" + color + "\":");
        devices.stream()
                .filter(device -> device.color().equalsIgnoreCase(color))
                .forEach(System.out::println);

        System.out.print("\nВведіть рік випуску для пошуку: ");
        int year = scanner.nextInt();
        System.out.println("Пристрої року випуску \"" + year + "\":");
        devices.stream()
                .filter(device -> device.year() == year)
                .forEach(System.out::println);

        System.out.print("\nВведіть мінімальну ціну для пошуку: ");
        int price = scanner.nextInt();
        System.out.println("Пристрої дорожче " + price + ":");
        devices.stream()
                .filter(device -> device.price() > price)
                .forEach(System.out::println);

        System.out.print("\nВведіть тип пристрою для пошуку: ");
        scanner.nextLine();
        String type = scanner.nextLine();
        System.out.println("Пристрої типу \"" + type + "\":");
        devices.stream()
                .filter(device -> device.type().equalsIgnoreCase(type))
                .forEach(System.out::println);

        System.out.print("\nВведіть початковий рік діапазону: ");
        int startYear = scanner.nextInt();
        System.out.print("Введіть кінцевий рік діапазону: ");
        int endYear = scanner.nextInt();
        System.out.println("Пристрої, випущені в діапазоні років " + startYear + "-" + endYear + ":");
        devices.stream()
                .filter(device -> device.year() >= startYear && device.year() <= endYear)
                .forEach(System.out::println);
    }
}
