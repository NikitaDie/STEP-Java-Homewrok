package com.example.nikitadeveloper.bookswebapp.config;

import com.example.nikitadeveloper.bookswebapp.entity.Book;
import com.example.nikitadeveloper.bookswebapp.util.HibernateUtil;
import jakarta.annotation.PostConstruct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @PostConstruct
    public void seedData() {
        try (Session session = sessionFactory.openSession()) {
            long count = ((Number) session.createQuery("select count(b.id) from Book b").uniqueResult()).longValue();

            if (count == 0) {
                Transaction tx = session.beginTransaction();

                List<Book> books = List.of(
                    new Book("The Hobbit", "J.R.R. Tolkien", 1937, "Fantasy", 310, "A fantasy novel about a hobbit."),
                    new Book("1984", "George Orwell", 1949, "Dystopian", 328, "A dystopian social science fiction novel."),
                    new Book("To Kill a Mockingbird", "Harper Lee", 1960, "Drama", 281, "A novel about racial injustice in the Deep South."),
                    new Book("Pride and Prejudice", "Jane Austen", 1813, "Romance", 279, "A romantic novel of manners."),
                    new Book("Moby-Dick", "Herman Melville", 1851, "Adventure", 585, "A novel about the voyage of the whaling ship Pequod.")
                );

                for (Book book : books) {
                    session.persist(book);
                }

                tx.commit();
                System.out.println("📚 Demo books seeded into the database.");
            } else {
                System.out.println("📚 Books already exist. Skipping seeding.");
            }
        }
    }
}