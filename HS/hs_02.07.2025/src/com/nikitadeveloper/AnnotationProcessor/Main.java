import testObjects.Event;
import testObjects.Order;
import testObjects.Product;
import testObjects.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User validUser = new User("john_doe", Arrays.asList("john@example.com"), "john.doe@example.com");
        System.out.println("Valid User: " + Validator.validate(validUser));

        // Test 2: Invalid User (null username, empty emails, invalid email)
        User invalidUser = new User(null, new ArrayList<>(), "invalid-email");
        System.out.println("Invalid User: " + Validator.validate(invalidUser));

        // Test 3: Valid Product
        Product validProduct = new Product("Laptop", BigDecimal.valueOf(500), "A great laptop");
        System.out.println("Valid Product: " + Validator.validate(validProduct));

        // Test 4: Invalid Product (price too low, description too short)
        Product invalidProduct = new Product("Mouse", BigDecimal.valueOf(0), "Hi");
        System.out.println("Invalid Product: " + Validator.validate(invalidProduct));

        // Test 5: Valid Event
        Event validEvent = new Event("Conference", LocalDateTime.now().plusDays(1));
        System.out.println("Valid Event: " + Validator.validate(validEvent));

        // Test 6: Invalid Event (past date)
        Event invalidEvent = new Event("Meeting", LocalDateTime.now().minusDays(1));
        System.out.println("Invalid Event: " + Validator.validate(invalidEvent));

        // Test 7: Valid Order
        List<Product> validItems = Arrays.asList(validProduct);
        Order validOrder = new Order("ORD123", validItems, validUser);
        System.out.println("Valid Order: " + Validator.validate(validOrder));

        // Test 8: Invalid Order (null orderId, empty items, invalid user)
        Order invalidOrder = new Order(null, new ArrayList<>(), invalidUser);
        System.out.println("Invalid Order: " + Validator.validate(invalidOrder));
    }
}