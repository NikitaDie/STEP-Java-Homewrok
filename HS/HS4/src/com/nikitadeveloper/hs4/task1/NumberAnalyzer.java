package com.nikitadeveloper.hs4.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberAnalyzer {
    public static void main(String[] args) {
        int size = 100;
        int min = -999;
        int max = 999;

        List<Integer> numbers = generateRandomNumbers(size, min, max);

        long positiveCount = numbers.stream().filter(n -> n > 0).count();
        long negativeCount = numbers.stream().filter(n -> n < 0).count();
        long twoDigitCount = numbers.stream().filter(n -> Math.abs(n) >= 10 && Math.abs(n) <= 99).count();
        long palindromeCount = numbers.stream().filter(NumberAnalyzer::isPalindrome).count();

        System.out.println("Список чисел: " + numbers);
        System.out.println("Кількість додатних чисел: " + positiveCount);
        System.out.println("Кількість від'ємних чисел: " + negativeCount);
        System.out.println("Кількість двозначних чисел: " + twoDigitCount);
        System.out.println("Кількість дзеркальних чисел: " + palindromeCount);
    }

    private static List<Integer> generateRandomNumbers(int size, int min, int max) {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            numbers.add(random.nextInt(max - min + 1) + min);
        }
        return numbers;
    }

    private static boolean isPalindrome(int number) {
        String str = String.valueOf(Math.abs(number));
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }
}
