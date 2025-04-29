package com.example.nikitadeveloper.bookswebapp.dao;

import com.example.nikitadeveloper.bookswebapp.dto.SearchCriteria;
import com.example.nikitadeveloper.bookswebapp.entity.Book;
import com.example.nikitadeveloper.bookswebapp.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class BookDAO {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Book> getAllBooks() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Book> books = session.createQuery("from Book", Book.class).getResultList();
            session.getTransaction().commit();
            return books;
        } catch (HibernateException e) {
            System.err.println("Error fetching all books: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Book getBookById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Book book = session.get(Book.class, id);
            session.getTransaction().commit();
            return book;
        } catch (HibernateException e) {
            System.err.println("Error fetching book by ID: " + e.getMessage());
            return null;
        }
    }

    public void saveBook(Book book) {
        if (book == null)
            throw new IllegalArgumentException("Book cannot be null");

        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.merge(book);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                System.err.println("Error saving book: " + e.getMessage());
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                throw new RuntimeException("Failed to save book", e);
            }
        } catch (Exception e) {
            System.err.println("Unexpected error in saveBook: " + e.getMessage());
            throw new RuntimeException("Unexpected error while saving book", e);
        }
    }

    public void deleteBook(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }

        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Book book = session.get(Book.class, id);
                if (book != null) {
                    session.remove(book);
                }
                session.getTransaction().commit();
            } catch (HibernateException e) {
                System.err.println("Error deleting book: " + e.getMessage());
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                throw new RuntimeException("Failed to delete book", e);
            }
        } catch (Exception e) {
            System.err.println("Unexpected error in deleteBook: " + e.getMessage());
            throw new RuntimeException("Unexpected error while deleting book", e);
        }
    }

    public List<Book> searchBooks(SearchCriteria criteria) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                String hql = buildHqlQuery(criteria);
                Query<Book> query = session.createQuery(hql, Book.class);
                setQueryParameters(query, criteria);
                List<Book> books = query.getResultList();
                session.getTransaction().commit();
                return books;
            } catch (HibernateException e) {
                System.err.println("Error searching books: " + e.getMessage());
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                return Collections.emptyList();
            }
        } catch (Exception e) {
            System.err.println("Unexpected error in searchBooks: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private String buildHqlQuery(SearchCriteria criteria) {
        StringBuilder hql = new StringBuilder("from Book");
        boolean hasCondition = false;

        if (criteria != null) {
            if (criteria.getTitle() != null && !criteria.getTitle().isEmpty()) {
                hql.append(hasCondition ? " and" : " where").append(" lower(title) like :title");
                hasCondition = true;
            }
            if (criteria.getAuthor() != null && !criteria.getAuthor().isEmpty()) {
                hql.append(hasCondition ? " and" : " where").append(" lower(author) like :author");
                hasCondition = true;
            }
            if (criteria.getPublicationYear() != null) {
                hql.append(hasCondition ? " and" : " where").append(" publicationYear = :publicationYear");
                hasCondition = true;
            }
            if (criteria.getGenre() != null && !criteria.getGenre().isEmpty()) {
                hql.append(hasCondition ? " and" : " where").append(" lower(genre) like :genre");
                hasCondition = true;
            }
            if (criteria.getPageCount() != null) {
                hql.append(hasCondition ? " and" : " where").append(" pageCount = :pageCount");
                hasCondition = true;
            }
            if (criteria.getDescription() != null && !criteria.getDescription().isEmpty()) {
                hql.append(hasCondition ? " and" : " where").append(" lower(description) like :description");
                hasCondition = true;
            }
        }

        return hql.toString();
    }

    private void setQueryParameters(Query<Book> query, SearchCriteria criteria) {
        if (criteria != null) {
            if (criteria.getTitle() != null && !criteria.getTitle().isEmpty()) {
                query.setParameter("title", "%" + criteria.getTitle().toLowerCase() + "%");
            }
            if (criteria.getAuthor() != null && !criteria.getAuthor().isEmpty()) {
                query.setParameter("author", "%" + criteria.getAuthor().toLowerCase() + "%");
            }
            if (criteria.getPublicationYear() != null) {
                query.setParameter("publicationYear", criteria.getPublicationYear());
            }
            if (criteria.getGenre() != null && !criteria.getGenre().isEmpty()) {
                query.setParameter("genre", "%" + criteria.getGenre().toLowerCase() + "%");
            }
            if (criteria.getPageCount() != null) {
                query.setParameter("pageCount", criteria.getPageCount());
            }
            if (criteria.getDescription() != null && !criteria.getDescription().isEmpty()) {
                query.setParameter("description", "%" + criteria.getDescription().toLowerCase() + "%");
            }
        }
    }
}