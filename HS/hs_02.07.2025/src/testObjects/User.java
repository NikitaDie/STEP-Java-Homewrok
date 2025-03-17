package testObjects;

import com.nikitadeveloper.AnnotationProcessor.MyAnotations;

import java.util.List;

public class User {
    @MyAnotations.NotNull(message = "Username cannot be null")
    private String username;

    @MyAnotations.NotEmpty(message = "Email list cannot be empty")
    @MyAnotations.Size(min = 1, max = 3, message = "Email list must have 1-3 entries")
    private List<String> emails;

    @MyAnotations.Email(message = "Invalid email format")
    private String primaryEmail;

    public User(String username, List<String> emails, String primaryEmail) {
        this.username = username;
        this.emails = emails;
        this.primaryEmail = primaryEmail;
    }

    public String getUsername() { return username; }
    public List<String> getEmails() { return emails; }
    public String getPrimaryEmail() { return primaryEmail; }
}
