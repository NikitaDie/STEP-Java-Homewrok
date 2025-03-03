package com.example.nikitadev.hs02222025.model.rest;

import lombok.Data;

@Data
public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0)
            throw new IllegalArgumentException("Denominator cannot be zero");

        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public String toString() {
        return String.format("%d/%d", numerator, denominator);
    }
}
