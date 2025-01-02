package com.nikitadeveloper.hs2.task1;

public class Pilot extends Human {
    private String pilotLicenseNumber;
    private int flightHours;

    public Pilot(String name, int age, String pilotLicenseNumber, int flightHours) {
        super(name, age);
        this.pilotLicenseNumber = pilotLicenseNumber;
        this.flightHours = flightHours;
    }

    public String getPilotLicenseNumber() {
        return pilotLicenseNumber;
    }

    public void setPilotLicenseNumber(String pilotLicenseNumber) {
        this.pilotLicenseNumber = pilotLicenseNumber;
    }

    public int getFlightHours() {
        return flightHours;
    }

    public void setFlightHours(int flightHours) {
        this.flightHours = flightHours;
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append(super.toString()).append("\n")
            .append("Pilot License Number: ").append(pilotLicenseNumber).append("\n")
            .append("Flight Hours: ").append(flightHours)
            .toString();
    }

}
