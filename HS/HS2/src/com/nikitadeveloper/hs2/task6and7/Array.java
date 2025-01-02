package com.nikitadeveloper.hs2.task6and7;

import java.util.Arrays;

public class Array implements IMath, ISort {
    private final int[] elements;

    public Array(int[] elements) {
        this.elements = elements;
    }

    @Override
    public int max() {
        int max = elements[0];
        for (int element : elements) {
            if (element > max) {
                max = element;
            }
        }
        return max;
    }

    @Override
    public int min() {
        int min = elements[0];
        for (int element : elements) {
            if (element < min) {
                min = element;
            }
        }
        return min;
    }

    @Override
    public float avg() {
        int sum = 0;
        for (int element : elements) {
            sum += element;
        }
        return (float) sum / elements.length;
    }

    @Override
    public void sortAsc() {
        Arrays.sort(elements);
    }

    @Override
    public void sortDesc() {
        Arrays.sort(elements);
        for (int i = 0; i < elements.length / 2; i++) {
            int temp = elements[i];
            elements[i] = elements[elements.length - 1 - i];
            elements[elements.length - 1 - i] = temp;
        }
    }

    public void display() {
        System.out.println(Arrays.toString(elements));
    }
}
