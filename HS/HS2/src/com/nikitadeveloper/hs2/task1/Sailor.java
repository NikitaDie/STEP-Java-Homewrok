package com.nikitadeveloper.hs2.task1;

public class Sailor extends Human{
    private String shipName;

    public Sailor(String name, int age, String shipName) {
        super(name, age);
        this.shipName = shipName;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    @Override
    public String toString() {
        return super.toString() + ", ShipName: " + shipName;
    }
}
