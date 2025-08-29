package com.example.book_store_management.repository;

import com.example.book_store_management.dto.ReviewDto;
import com.example.book_store_management.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepo extends JpaRepository<Review, Integer> {

    List<Review> findByBookId(int bookId);
}
