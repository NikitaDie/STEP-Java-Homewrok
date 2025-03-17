package com.example.nikitadev.hs02202025.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileUploadService {
    private final Path uploadDir;

    public FileUploadService(@Value("${upload.dir}") String uploadDir) throws IOException {
        this.uploadDir = Path.of(uploadDir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.uploadDir);
        } catch (IOException e) {
            throw new IOException("Could not create upload directory: " + uploadDir, e);
        }
    }

    public String uploadFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }

        String originalFilename = file.getOriginalFilename();
        String sanitizedFilename = originalFilename.replaceAll("[^a-zA-Z0-9._-]", "_");
        String fileName = UUID.randomUUID() + "_" + sanitizedFilename;
        Path filePath = uploadDir.resolve(fileName);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Failed to save file: " + fileName, e);
        }

        return "/uploads/" + fileName;
    }

    public String uploadFile(MultipartFile file, String existingImagePath) throws IOException {
        if (file.isEmpty()) {
            return existingImagePath;
        }

        if (existingImagePath != null && !existingImagePath.isBlank() && existingImagePath.startsWith("/uploads/")) {
            Path oldFilePath = uploadDir.resolve(existingImagePath.substring("/uploads/".length()));
            if (Files.exists(oldFilePath)) {
                Files.delete(oldFilePath);
            }
        }

        return uploadFile(file);
    }
}
