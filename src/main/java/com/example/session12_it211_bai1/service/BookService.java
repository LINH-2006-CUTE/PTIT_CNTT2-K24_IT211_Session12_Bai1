package com.example.session12_it211_bai1.service;

import com.example.session12_it211_bai1.model.entity.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {
    private final List<Book> books = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Book> findAll() {
        return books;
    }

    public Optional<Book> findById(Long id) {
        return books.stream().filter(b -> b.getId().equals(id)).findFirst();
    }

    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(counter.getAndIncrement());
        }
        books.add(book);
        return book;
    }

    public boolean update(Long id, Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id)) {
                book.setId(id);
                books.set(i, book);
                return true;
            }
        }
        return false;
    }

    public boolean delete(Long id) {
        return books.removeIf(b -> b.getId().equals(id));
    }
}
