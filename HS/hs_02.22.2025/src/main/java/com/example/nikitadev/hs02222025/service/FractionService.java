package com.example.nikitadev.hs02222025.service;

import com.example.nikitadev.hs02222025.model.rest.Fraction;
import org.springframework.stereotype.Service;

@Service
public class FractionService {

    private int gcd(int a, int b) {
        return b == 0 ? Math.abs(a) : gcd(b, a % b);
    }

    public boolean isProper(Fraction fraction) {
        return Math.abs(fraction.getNumerator()) < Math.abs(fraction.getDenominator());
    }

    public Fraction reduce(Fraction fraction) {
        int gcd = gcd(fraction.getNumerator(), fraction.getDenominator());
        return new Fraction(
            fraction.getNumerator() / gcd,
            fraction.getDenominator() / gcd
        );
    }

    public Fraction add(Fraction f1, Fraction f2) {
        int newNum = f1.getNumerator() * f2.getDenominator() + f2.getNumerator() * f1.getDenominator();
        int newDen = f1.getDenominator() * f2.getDenominator();
        return reduce(new Fraction(newNum, newDen));
    }

    public Fraction subtract(Fraction f1, Fraction f2) {
        int newNum = f1.getNumerator() * f2.getDenominator() - f2.getNumerator() * f1.getDenominator();
        int newDen = f1.getDenominator() * f2.getDenominator();
        return reduce(new Fraction(newNum, newDen));
    }

    public Fraction multiply(Fraction f1, Fraction f2) {
        int newNum = f1.getNumerator() * f2.getNumerator();
        int newDen = f1.getDenominator() * f2.getDenominator();
        return reduce(new Fraction(newNum, newDen));
    }

    public Fraction divide(Fraction f1, Fraction f2) {
        if (f2.getNumerator() == 0)
            throw new ArithmeticException("Division by zero");

        int newNum = f1.getNumerator() * f2.getDenominator();
        int newDen = f1.getDenominator() * f2.getNumerator();
        return reduce(new Fraction(newNum, newDen));
    }
}
