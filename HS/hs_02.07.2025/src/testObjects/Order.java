package testObjects;

import com.nikitadeveloper.AnnotationProcessor.MyAnotations;

import java.util.List;

public class Order {
    @MyAnotations.NotNull
    private String orderId;

    @MyAnotations.NotEmpty(message = "Items cannot be empty")
    private List<Product> items;

    @MyAnotations.NotNull
    private User customer;

    public Order(String orderId, List<Product> items, User customer) {
        this.orderId = orderId;
        this.items = items;
        this.customer = customer;
    }
}