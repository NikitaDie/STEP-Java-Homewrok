package testObjects;

import com.nikitadeveloper.AnnotationProcessor.MyAnotations;

import java.math.BigDecimal;

public class Product {
    @MyAnotations.NotNull
    private String name;

    @MyAnotations.Min(value = 1, message = "Price must be at least 1")
    @MyAnotations.Max(value = 1000, message = "Price must not exceed 1000")
    private BigDecimal price;

    @MyAnotations.Size(min = 5, max = 20, message = "Description must be 5-20 characters")
    private String description;

    public Product(String name, BigDecimal price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}