package com.nikitadeveloper.hs3.task3;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public class TaxInspectionDatabase {
    private static final Map<String, Person> personStore = new HashMap<>();
    private static final Map<String, Fine> fineStore = new HashMap<>();

    public static Person getPerson(String personId) {
        return personStore.get(personId);
    }

    public static String addPerson(String name) {
        String personId = generateId();

        personStore.put(personId, new Person(personId, name));

        System.out.println("Person added successfully. Generated ID: " + personId);
        return personId;
    }

    public static void addFine(String personId, FineType type, double amount, String city, String description) {
        Optional<Person> person = findPersonById(personId);
        if (person.isEmpty()) {
            System.out.println("Person with this ID does not exist.");
            return;
        }

        String fineId = generateId();

        fineStore.put(fineId, new Fine(fineId, type, amount, city, description));
        person.get().addFine(fineId);
        System.out.println("Fine added successfully.");
    }

    public static void removeFine(String personId, String fineId) {
        Optional<Person> person = findPersonById(personId);
        if (person.isEmpty()) {
            System.out.println("Person with this ID does not exist.");
            return;
        }

        if (!fineStore.containsKey(fineId)) {
            System.out.println("Fine with this ID does not exist.");
            return;
        }

        person.get().removeFine(fineId);
        fineStore.remove(fineId);
        System.out.println("Fine removed successfully.");
    }



    public static void updateFine(String id, Map<String, String> updates) {
        Fine fine = findFineById(id).orElse(null);
        if (fine == null) {
            return;
        }

        Map<String, Consumer<String>> updateActions = Map.of(
            "type", value -> fine.setType(FineType.valueOf(value.toUpperCase())),
            "amount", value -> fine.setAmount(Double.parseDouble(value)),
            "description", fine::setDescription,
            "city", fine::setCity
        );

        processUpdates(updates, updateActions);
    }

    public static void updatePerson(String id, Map<String, String> updates) {
        Person person = findPersonById(id).orElse(null);
        if (person == null) {
            return;
        }

        Map<String, Consumer<String>> updateActions = Map.of(
            "name", person::setName
        );

        processUpdates(updates, updateActions);
    }

    public static void printDatabase() {
        System.out.println("=== Повна база даних ===");
        if (personStore.isEmpty()) {
            System.out.println("База даних пуста.");
        } else {
            personStore.values().forEach(System.out::println);
            fineStore.values().forEach(System.out::println);
        }
    }

    public static void printDataByPersonId(String personId) {
        Optional<Person> person = findPersonById(personId);
        if (person.isEmpty()) {
            System.out.println("Людина з таким ID не знайдена.");
            return;
        }

        System.out.println("=== Дані для людини ID: " + personId + " ===");
        System.out.println(person.get());
        person.get().getFines().forEach(fineId -> {
            Fine fine = fineStore.get(fineId);
            if (fine != null) {
                System.out.println(fine);
            }
        });
    }

    public static void printDataByFineType(String type) {
        System.out.println("=== Дані по типу штрафу: " + type + " ===");

        fineStore.values().stream()
            .filter(fine -> type.equalsIgnoreCase(fine.getType().toString()))
            .forEach(fine -> personStore.values().stream()
                .filter(person -> person.getFines().contains(fine.getId()))
                .forEach(person -> System.out.println("PersonId: " + person.getId() + ", PersonName: " + person.getName()+ " " + fine)));
    }

    public static void printDataByCity(String city) {
        System.out.println("=== Дані для міста: " + city + " ===");

        fineStore.values().stream()
            .filter(fine -> fine.getCity().equalsIgnoreCase(city))
            .forEach(fine -> personStore.values().stream()
                .filter(person -> person.getFines().contains(fine.getId()))
                .forEach(person -> System.out.println("PersonId: " + person.getId() + ", PersonName: " + person.getName()+ " " + fine)));
    }

    private static Optional<Person> findPersonById(String id) {
        return Optional.ofNullable(personStore.get(id));
    }

    private static Optional<Fine> findFineById(String id) {
        return Optional.ofNullable(fineStore.get(id));
    }

    private static void processUpdates(Map<String, String> updates, Map<String, Consumer<String>> updateActions) {
        for (Map.Entry<String, String> entry : updates.entrySet()) {
            String key = entry.getKey().toLowerCase();
            String value = entry.getValue();

            Consumer<String> updateAction = updateActions.get(key);
            if (updateAction == null) {
                System.out.println("Invalid field: " + key);
                continue;
            }

            updateAction.accept(value);
            System.out.println(key + " updated successfully.");
        }
    }

    private static String generateId() {
        return UUID.randomUUID().toString();
    }
}
