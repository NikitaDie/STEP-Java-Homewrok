package com.nikitadeveloper.hs2;

import com.nikitadeveloper.hs2.task6and7.Array;

public class Main {
    public static void main(String[] args) {
        int[] numbers = {10, 50, 20, 40, 30};
        Array array = new Array(numbers);

        System.out.println("Масив перед сортуванням:");
        array.display();

        array.sortAsc();
        System.out.println("Масив після сортування по зростанню:");
        array.display();

        array.sortDesc();
        System.out.println("Масив після сортування по спаданню:");
        array.display();

        System.out.println("Максимум: " + array.max()); // 50
        System.out.println("Мінімум: " + array.min()); // 10
        System.out.println("Середнє арифметичне: " + array.avg()); // 30.0
    }
}