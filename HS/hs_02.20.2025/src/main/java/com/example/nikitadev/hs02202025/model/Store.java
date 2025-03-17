package com.example.nikitadev.hs02202025.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@NoArgsConstructor
@Table(name = "stores")
public class Store {
    @Id
    private UUID id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Address is required")
    @Size(max = 200, message = "Address must not exceed 200 characters")
    private String address;

    @Pattern(regexp = "^\\+?[1-9]\\d{0,2}\\d{6,12}$", message = "Phone must be a valid number (e.g., +1234567890)")
    private String phone;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Website URL is required")
    @Pattern(regexp = "^(https?://)?([a-zA-Z0-9-]+\\.)*[a-zA-Z0-9-]+\\.[a-zA-Z]{2,63}(/\\S*)?$",
        message = "Invalid website URL")
    private String website;

//    @NotBlank(message = "Category is required")
    private StoreCategory category;

    @Size(max = 2000, message = "Description must not exceed 2000 characters")
    private String description;

    private String imagePath;

    public Store(String name, String address, String phone, String email,
                 String website, StoreCategory category, String description, String imagePath) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.category = category;
        this.description = description;
        this.imagePath = imagePath;
    }
}
