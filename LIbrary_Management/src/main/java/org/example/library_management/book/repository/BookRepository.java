package org.example.library_management.book.repository;

import org.example.library_management.book.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/** In-memory storage for books; replaceable by a database-backed repository later. */
@Repository
public class BookRepository {

    private final ConcurrentMap<Integer, Book> books = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(nextId.getAndIncrement());
        } else {
            nextId.updateAndGet(current -> Math.max(current, book.getId() + 1));
        }
        books.put(book.getId(), book);
        return book;
    }

    public List<Book> findAll() {
        List<Book> result = new ArrayList<>(books.values());
        result.sort(Comparator.comparing(Book::getId));
        return result;
    }

    public Optional<Book> findById(Integer id) {
        return Optional.ofNullable(books.get(id));
    }

    public boolean deleteById(Integer id) {
        return books.remove(id) != null;
    }
}
