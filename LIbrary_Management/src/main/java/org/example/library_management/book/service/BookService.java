package org.example.library_management.book.service;

import org.example.library_management.book.exception.BookNotFoundException;
import org.example.library_management.book.model.Book;
import org.example.library_management.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book addBook(Book book) {
        book.setId(null);
        return repository.save(book);
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book getBookById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book updateBook(Integer id, Book details) {
        Book book = getBookById(id);
        book.setTitle(details.getTitle());
        book.setAuthor(details.getAuthor());
        book.setCategory(details.getCategory());
        book.setAvailableCopies(details.getAvailableCopies());
        return repository.save(book);
    }

    public void deleteBook(Integer id) {
        if (!repository.deleteById(id)) {
            throw new BookNotFoundException(id);
        }
    }
}
