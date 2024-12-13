package com.nikitadeveloper.hs1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            //task_1();
            //task_2(scanner);
            //task_3(scanner);
            //task_4(scanner);
            //task_5(scanner);
            //task_6(scanner);
            //task_7(scanner);
            //task_8(scanner);
            //task_9();
            //task_10();
            //task_11();
            task_12(scanner);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void task_1() {
        String[] lines = {
            "\"Your time is limited,", "so don’t waste it", "living someone else’s life\"", "Steve Jobs"
        };

        StringBuilder tabulators = new StringBuilder();
        for (String line : lines) {
            System.out.println(tabulators + line);
            tabulators.append("\t");
        }
    }

    public static void task_2(Scanner scanner) {
        double value = getDoubleInput(scanner, "Enter a value: ");
        double percent = getDoubleInput(scanner, "Enter a percentage: ");
        System.out.println(percent + "% of " + value + " = " + (value * percent / 100));
    }

    public static void task_3(Scanner scanner) {
        int num1 = getIntInput(scanner, "Enter three numbers: ");
        int num2 = getIntInput(scanner);
        int num3 = getIntInput(scanner);

        System.out.println("Formed number: " + num1 + num2 + num3);
    }

    public static void task_4(Scanner scanner) {
        int number;
        do {
            number = getIntInput(scanner, "Enter a six-digit number: ");
            if (number < 100000 || number > 999999) {
                System.out.println("Error: Not a six-digit number. Please try again.");
            }
        } while (number < 100000 || number > 999999);

        String input = String.valueOf(number);
        char[] chars = input.toCharArray();

        char temp1 = chars[0];
        char temp2 = chars[1];
        chars[0] = chars[5];
        chars[1] = chars[4];
        chars[5] = temp1;
        chars[4] = temp2;

        System.out.println("Modified number: " + new String(chars));
    }

    public static void task_5(Scanner scanner) {
        int month = getIntInput(scanner, "Enter a month number (1-12): ");

        switch (month) {
            case 1, 2, 12 -> System.out.println("Winter");
            case 3, 4, 5 -> System.out.println("Spring");
            case 6, 7, 8 -> System.out.println("Summer");
            case 9, 10, 11 -> System.out.println("Autumn");
            default -> System.out.println("Error: Month must be between 1 and 12.");
        }
    }

    public static void task_6(Scanner scanner) {
        double meters = getDoubleInput(scanner, "Enter the number of meters: ");
        int choice = getIntInput(scanner, """
            Choose a conversion unit:
            1 - Miles
            2 - Inches
            3 - Yards
            Enter your choice:\s""");

        if (choice < 1 || choice > 3) {
            System.out.println("Error: Invalid choice. Please select a number between 1 and 3.");
        }

        switch (choice) {
            case 1 -> System.out.printf("%.2f meters = %.5f miles%n", meters, meters / 1609);
            case 2 -> System.out.printf("%.2f meters = %.2f inches%n", meters, meters * 39.37);
            case 3 -> System.out.printf("%.2f meters = %.2f yards%n", meters, meters * 1.094);
        }
    }

    public static void task_7(Scanner scanner) {
        int start = getIntInput(scanner, "Enter two numbers: ");
        int end = getIntInput(scanner);

        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        start = start % 2 == 0 ? start + 1 : start;

        for (int i = start; i <= end; i += 2)
            System.out.print(i + " ");

        System.out.println();
    }

    public static void task_8(Scanner scanner) {
        int mulStart = getIntInput(scanner, "Enter start and end values for the multiplication table: ");
        int mulEnd = getIntInput(scanner);

        for (int i = mulStart; i <= mulEnd; i++) {
            for (int j = 1; j <= 10; j++) {
                System.out.print(i + "*" + j + " = " + (i * j) + "\t");
            }
            System.out.println();
        }
    }

    public static void task_9() {
        int[] array = new Random().ints(10, -10, 10).toArray();

        int min = Arrays.stream(array).min().orElse(0);
        int max = Arrays.stream(array).max().orElse(0);

        long negatives = Arrays.stream(array).filter(n -> n < 0).count();
        long positives = Arrays.stream(array).filter(n -> n > 0).count();
        long zeros = Arrays.stream(array).filter(n -> n == 0).count();

        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Minimum value: " + min);
        System.out.println("Maximum value: " + max);
        System.out.println("Negative count: " + negatives);
        System.out.println("Positive count: " + positives);
        System.out.println("Zero count: " + zeros);
    }

    public static void task_10() {
        int[] array = new Random().ints(10, -10, 10).toArray();

        int[] evens = Arrays.stream(array).filter(n -> n % 2 == 0).toArray();
        int[] odds = Arrays.stream(array).filter(n -> n % 2 != 0).toArray();
        int[] negativesArr = Arrays.stream(array).filter(n -> n < 0).toArray();
        int[] positivesArr = Arrays.stream(array).filter(n -> n > 0).toArray();

        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Evens: " + Arrays.toString(evens));
        System.out.println("Odds: " + Arrays.toString(odds));
        System.out.println("Negatives: " + Arrays.toString(negativesArr));
        System.out.println("Positives: " + Arrays.toString(positivesArr));
    }

    public static void task_11() {
        drawLine(10, 'h', '*');
        drawLine(5, 'v', '#');
    }

    public static void task_12(Scanner scanner) {

        int[] toSort = new Random().ints(10, 1, 100).toArray();
        System.out.println("Array before sorting: " + Arrays.toString(toSort));

        int sortChoice = getIntInput(scanner, "Sort ascending (1) or descending (2): ");

        Arrays.sort(toSort);

        if (sortChoice == 2) {
            for (int i = 0; i < toSort.length / 2; i++) {
                int temp = toSort[i];
                toSort[i] = toSort[toSort.length - 1 - i];
                toSort[toSort.length - 1 - i] = temp;
            }
        }

        System.out.println("Sorted array: " + Arrays.toString(toSort));
    }

    private static void drawLine(int length, char direction, char symbol) {
        if (direction == 'h') {
            for (int i = 0; i < length; i++) {
                System.out.print(symbol);
            }
            System.out.println();
        } else if (direction == 'v') {
            for (int i = 0; i < length; i++) {
                System.out.println(symbol);
            }
        } else {
            System.out.println("Invalid direction.");
        }
    }

    private static double getDoubleInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    private static int getIntInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static int getIntInput(Scanner scanner) {
        return getIntInput(scanner, "");
    }
}
