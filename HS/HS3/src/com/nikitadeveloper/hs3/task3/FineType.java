package com.nikitadeveloper.hs3.task3;

public enum FineType {
    SPEEDING("Speeding Violation"),
    PARKING("Illegal Parking"),
    DUI("Driving Under Influence"),
    NO_INSURANCE("Driving Without Insurance"),
    RED_LIGHT("Running a Red Light"),
    VEHICLE_MAINTENANCE("Vehicle Maintenance Violation"),
    TAX_EVASION("Tax Evasion"),
    LITTERING("Littering in Public"),
    PUBLIC_DISTURBANCE("Public Disturbance");

    private final String description;

    FineType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

