package com.example.session12_it211_bai1.repository;

import com.example.session12_it211_bai1.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
