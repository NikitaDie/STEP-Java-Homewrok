package com.nikitadeveloper.hs4.task5;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Corporation {
    private final List<Employee> employees = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new JavaTimeModule());

    public synchronized void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public synchronized void editEmployee(int id, Employee updatedEmployee) {
        employees.stream()
            .filter(e -> e.getId() == id)
            .findFirst()
            .ifPresent(e -> {
                e.setFirstName(updatedEmployee.getFirstName());
                e.setLastName(updatedEmployee.getLastName());
                e.setBirthDate(updatedEmployee.getBirthDate());
            });
    }

    public synchronized void removeEmployee(int id) {
        employees.removeIf(e -> e.getId() == id);
    }

    public synchronized List<Employee> searchByLastName(String lastName) {
        return employees.stream()
            .filter(e -> e.getLastName().equalsIgnoreCase(lastName))
            .toList()
            .stream()
            .collect(Collectors.collectingAndThen(Collectors.toList(), List::copyOf));
    }

    public synchronized List<Employee> filterByAgeAndLastName(Character initial, Integer minAge) {
        return employees.stream()
            .filter(e -> (initial == null ||
                Character.toLowerCase(e.getLastName().charAt(0)) == Character.toLowerCase(initial)))
            .filter(e -> (minAge == null || e.getAge() >= minAge))
            .toList()
            .stream()
            .collect(Collectors.collectingAndThen(Collectors.toList(), List::copyOf));
    }

    public synchronized List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    public String serializeEmployeesToJson() {
        try {
            return objectMapper.writeValueAsString(employees);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing employees to JSON", e);
        }
    }

    public void deserializeEmployeesFromJson(String jsonString) {
        try {
            List<Employee> loadedEmployees = objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(List.class, Employee.class));
            synchronized (this) {
                employees.clear();
                employees.addAll(loadedEmployees);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing employees from JSON", e);
        }
    }
}
