package testObjects;

import com.nikitadeveloper.AnnotationProcessor.MyAnotations;

import java.time.LocalDateTime;

public class Event {
    @MyAnotations.NotNull
    private String title;

    @MyAnotations.Future(message = "Event date must be in the future")
    private LocalDateTime date;

    public Event(String title, LocalDateTime date) {
        this.title = title;
        this.date = date;
    }
}