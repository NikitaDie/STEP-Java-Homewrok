package com.example.nikitadeveloper.bookswebapp.service;

import com.example.nikitadeveloper.bookswebapp.dao.BookDAO;
import com.example.nikitadeveloper.bookswebapp.dto.SearchCriteria;
import com.example.nikitadeveloper.bookswebapp.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookDAO bookDAO;

    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public Book getBookById(Long id) {
        return bookDAO.getBookById(id);
    }

    public void saveBook(Book book) {
        bookDAO.saveBook(book);
    }

    public void deleteBook(Long id) {
        bookDAO.deleteBook(id);
    }

    public List<Book> searchBooks(SearchCriteria criteria) {
        return bookDAO.searchBooks(criteria);
    }
}