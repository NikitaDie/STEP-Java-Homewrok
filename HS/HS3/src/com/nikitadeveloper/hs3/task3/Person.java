package com.nikitadeveloper.hs3.task3;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private final String id;
    private String name;
    private final List<String> finesIds;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
        this.finesIds = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void addFine(String fineId) {
        finesIds.add(fineId);
    }

    public void removeFine(String fineId) {
        finesIds.remove(fineId);
    }

    public List<String> getFines() {
        return finesIds;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Fines: " + finesIds;
    }
}
