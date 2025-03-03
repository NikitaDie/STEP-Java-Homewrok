package com.example.nikitadev.hs02172025.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
    private final UUID id = UUID.randomUUID();
    private String firstName = "";
    private String lastName = "";
    private String middleName;
    private String phone;
    private String email;
    private String blogUrl;
    private String notes;

    public String getFullName() {
        if (middleName == null || middleName.trim().isEmpty()) {
            return String.format("%s %s", lastName, firstName);
        } else {
            return String.format("%s %s %s", lastName, firstName, middleName);
        }
    }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty())
            throw new IllegalArgumentException("Full name cannot be empty");

        String[] nameParts = fullName.trim().split("\\s+");

        if (nameParts.length < 2)
            throw new IllegalArgumentException("Full name must contain at least two parts (first and last name)");

        this.lastName = nameParts[0];
        this.firstName = nameParts[1];
        this.middleName = nameParts.length > 2 ? String.join(" ",
            java.util.Arrays.copyOfRange(nameParts, 2, nameParts.length)) : "";
    }
}
