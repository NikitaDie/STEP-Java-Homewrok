package com.nikitadeveloper.hs4.task5.utils;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.CompletableFuture;

public class FileManager {
    public CompletableFuture<Boolean> saveToFile(String jsonString, String filePath) {
        return CompletableFuture.supplyAsync(() -> {
            Path path = Paths.get(filePath);
            try {
                if (path.getParent() == null) {
                    ConsoleLogger.logError("Invalid file path: " + filePath);
                    return false;
                }

                Files.createDirectories(path.getParent());
                Files.writeString(path, jsonString);
            } catch (AccessDeniedException e) {
                ConsoleLogger.logError("Access denied to directory: " + filePath);
                return false;
            } catch (IOException e) {
                ConsoleLogger.logError("Error saving data to file: " + filePath, e);
                return false;
            }

            return true;
        });

    }

    public CompletableFuture<String> loadFromFile(String filePath) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new String(Files.readAllBytes(Paths.get(filePath)));
            } catch (NoSuchFileException e) {
                ConsoleLogger.logWarning("File not found: " + filePath);
            } catch (AccessDeniedException e) {
                ConsoleLogger.logError("Access denied to file: " + filePath, e);
            }
            catch (IOException e) {
                ConsoleLogger.logError("Error reading data from file: " + filePath, e);
            }

            return "";
        });
    }
}
