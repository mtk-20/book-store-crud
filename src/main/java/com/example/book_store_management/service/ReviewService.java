package com.example.book_store_management.service;

import com.example.book_store_management.dto.ReviewDto;
import com.example.book_store_management.entity.Book;
import com.example.book_store_management.entity.Review;
import com.example.book_store_management.mapper.CustomUtils;
import com.example.book_store_management.repository.BookRepo;
import com.example.book_store_management.repository.ReviewRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepo reviewRepo;
    private final BookRepo bookRepo;
    private final CustomUtils customUtils;

    public ReviewDto addReview(ReviewDto reviewDto, String userName) {
        Book book = bookRepo.findById(reviewDto.getBookId()).orElseThrow(() -> new IllegalStateException("Book not found."));
        Review review = customUtils.toReviewEntity(reviewDto, book);
        review.setUserName(userName);
        return customUtils.toReviewDto(reviewRepo.save(review));
    }

    public List<ReviewDto> getReviewsByBookId(int bookId) {
        List<Review> reviews = reviewRepo.findByBookId(bookId);
        if (reviews.isEmpty()) {
            throw new EntityNotFoundException("No review for this book id.");
        }
        return reviews.stream().map(customUtils::toReviewDto).collect(Collectors.toList());
    }

    public List<ReviewDto> getReviewForBook() {
        return reviewRepo.findAll().stream().map(customUtils::toReviewDto).collect(Collectors.toList());
    }

    public void deleteReview(int id) {
        reviewRepo.deleteById(id);
    }
}
