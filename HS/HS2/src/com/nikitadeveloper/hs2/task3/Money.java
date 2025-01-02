package com.nikitadeveloper.hs2.task3;

public class Money {
    private int wholePart;
    private int fractionalPart;
    private Currency currency;

    public Money(int wholePart, int fractionalPart, Currency currency) {
        this(currency);
        this.wholePart = wholePart;
        this.fractionalPart = fractionalPart;
    }

    public Money(Currency currency) {
        this.currency = currency;
    }

    public int getWholePart() {
        return wholePart;
    }

    public void setWholePart(int wholePart) {
        this.wholePart = wholePart;
    }

    public int getFractionalPart() {
        return fractionalPart;
    }

    public int getAmountInCents() {
        return this.wholePart * 100 + this.fractionalPart;
    }

    public void setFractionalPart(int fractionalPart) {
        if (fractionalPart >= 100 || fractionalPart < 0) {
            int wholeAdjustment = fractionalPart / 100;
            this.fractionalPart = fractionalPart % 100;

            this.wholePart += wholeAdjustment;
        } else {
            this.fractionalPart = fractionalPart;
        }
    }

    public void subtract(Money amount) {
        int totalCents = this.getAmountInCents();
        int subtractCents = amount.getAmountInCents();
        int resultCents = totalCents - subtractCents;

        if (resultCents < 0) {
            System.out.println("There is not enough money for this action.");
            return;
        }

        this.setFractionalPart(resultCents);
    }

    public void add(Money amount) {
        int totalCents = this.getAmountInCents();
        int addedCents = amount.getAmountInCents();
        int resultCents = totalCents + addedCents;

        this.setFractionalPart(resultCents);
    }

    @Override
    public String toString() {
        return wholePart + "." + String.format("%02d", fractionalPart) + " " + currency.toString();
    }
}
