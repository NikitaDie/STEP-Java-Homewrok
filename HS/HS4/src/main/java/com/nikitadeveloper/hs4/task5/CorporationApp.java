package com.nikitadeveloper.hs4.task5;

import com.nikitadeveloper.hs4.task5.utils.ConsoleLogger;
import com.nikitadeveloper.hs4.task5.utils.FileManager;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class CorporationApp {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final String FILE_PATH  = Paths.get(System.getProperty("user.dir"), "data", "employees.json").toString();

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Corporation corporation = new Corporation();
        FileManager fileManager = new FileManager();

        while (true) {
            System.out.println("\n=== Corporation Management System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. Edit Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Search Employee by Last Name");
            System.out.println("5. Filter by Age or Last Name Initial");
            System.out.println("6. View All Employees");
            System.out.println("7. Save Data to File");
            System.out.println("8. Load Data from File");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                ConsoleLogger.printProgramMessage("Invalid input! Please enter a number between 1 and 8.");
                continue;
            }

            try {
                switch (choice) {
                    case 1 -> addEmployee(corporation);
                    case 2 -> editEmployee(corporation);
                    case 3 -> removeEmployee(corporation);
                    case 4 -> searchEmployee(corporation);
                    case 5 -> filterEmployees(corporation);
                    case 6 -> viewAllEmployees(corporation);
                    case 7 -> saveData(corporation, fileManager);
                    case 8 -> loadData(corporation, fileManager);
                    case 9 -> {
                        ConsoleLogger.printProgramMessage("Saving data before exiting...");
                        saveData(corporation, fileManager, FILE_PATH);
                        ConsoleLogger.printProgramMessage("Goodbye!");
                        return;
                    }
                    default -> ConsoleLogger.printProgramMessage("Invalid option! Please choose between 1 and 9.");
                }
            } catch (NumberFormatException e) {
                ConsoleLogger.printResponse("Invalid input!");
            } catch (Exception e) {
                ConsoleLogger.logError("An error occurred: " + e.getMessage());
            }

        }
    }

    private static void addEmployee(Corporation corporation) {
        ConsoleLogger.printProgramMessage("Enter First Name: ");
        String firstName = scanner.nextLine().trim();

        ConsoleLogger.printProgramMessage("Enter Last Name: ");
        String lastName = scanner.nextLine().trim();

        ConsoleLogger.printProgramMessage("Enter Birth Date (dd-MM-yyyy): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine().trim(), FORMATTER);

        Employee employee = new Employee(firstName, lastName, birthDate);
        corporation.addEmployee(employee);
        ConsoleLogger.printResponse("Employee added successfully!");
    }

    private static void editEmployee(Corporation corporation) {
        ConsoleLogger.printProgramMessage("Enter Employee ID to edit: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        ConsoleLogger.printProgramMessage("Enter New First Name: ");
        String firstName = scanner.nextLine().trim();

        ConsoleLogger.printProgramMessage("Enter New Last Name: ");
        String lastName = scanner.nextLine().trim();

        ConsoleLogger.printProgramMessage("Enter New Birth Date (dd-MM-yyyy): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine().trim(), FORMATTER);

        Employee updatedEmployee = new Employee(firstName, lastName, birthDate);
        corporation.editEmployee(id, updatedEmployee);
        ConsoleLogger.printResponse("Employee updated successfully!");
    }

    private static void removeEmployee(Corporation corporation) {
        ConsoleLogger.printProgramMessage("Enter Employee ID to remove: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        corporation.removeEmployee(id);
        ConsoleLogger.printResponse("Employee removed successfully!");
    }

    private static void searchEmployee(Corporation corporation) {
        ConsoleLogger.printProgramMessage("Enter Last Name to search: ");
        String lastName = scanner.nextLine().trim();

        List<Employee> results = corporation.searchByLastName(lastName);
        if (results.isEmpty()) {
            ConsoleLogger.printResponse("No employees found with last name: " + lastName);
        } else {
            results.forEach(e -> ConsoleLogger.printResponse(e.toString()));
        }
    }

    private static void filterEmployees(Corporation corporation) {
        ConsoleLogger.printProgramMessage("Enter initial letter of Last Name (or press Enter to skip): ");
        String input = scanner.nextLine().trim();
        Character initial = input.isEmpty() ? null : input.charAt(0);

        ConsoleLogger.printProgramMessage("Enter minimum age (or press Enter to skip): ");
        String ageInput = scanner.nextLine().trim();
        Integer minAge = ageInput.isEmpty() ? null : Integer.parseInt(ageInput);

        List<Employee> results = corporation.filterByAgeAndLastName(initial, minAge);
        if (results.isEmpty()) {
            ConsoleLogger.printResponse("No matching employees found.");
        } else {
            results.forEach(System.out::println);
        }
    }

    private static void viewAllEmployees(Corporation corporation) {
        List<Employee> employees = corporation.getAllEmployees();
        if (employees.isEmpty()) {
            ConsoleLogger.printResponse("No employees found.");
        } else {
            employees.forEach(e -> ConsoleLogger.printResponse(e.toString()));
        }
    }

    private static void saveData(Corporation corporation, FileManager fileManager) {
        ConsoleLogger.printProgramMessage("Enter file path to save employees to: ");
        String filePath = scanner.nextLine().trim();
        saveData(corporation, fileManager, filePath);
    }

    private static void saveData(Corporation corporation, FileManager fileManager, String filePath) {
        String jsonString = corporation.serializeEmployeesToJson();

        boolean isSaved = fileManager.saveToFile(jsonString, filePath).join();

        ConsoleLogger.printResponse(isSaved ? ("Data saved successfully! to: " + filePath) : "Failed to save data.");
    }

    private static void loadData(Corporation corporation, FileManager fileManager) {
        ConsoleLogger.printProgramMessage("Enter file path to load employees from: ");
        String filePath = scanner.nextLine().trim();

        CompletableFuture<String> loadTask = fileManager.loadFromFile(filePath);
        loadTask.join();

        String jsonString = loadTask.join();
        if (jsonString != null) {
            corporation.deserializeEmployeesFromJson(jsonString);
            ConsoleLogger.printResponse("Data loaded successfully!");
        } else {
            ConsoleLogger.printResponse("Failed to load data.");
        }
    }
}