package com.example.book_store_management.repository;

import com.example.book_store_management.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Integer> {
}
