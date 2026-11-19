package org.example.library_management.book.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Integer id) {
        super("Book with id " + id + " was not found");
    }
}
