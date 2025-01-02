package com.nikitadeveloper.hs2.task3;

public enum Currency {
    USD,
    EUR,
    UAH;

    @Override
    public String toString() {
        return switch (this) {
            case USD -> "USD";
            case EUR -> "EUR";
            case UAH -> "UAH";
            default -> "Unknown Currency";
        };
    }
}
