package com.example.book_store_management.controller;

import com.example.book_store_management.dto.ReviewDto;
import com.example.book_store_management.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping()
    public List<ReviewDto> getAllReviews() {
        return reviewService.getReviewForBook();
    }

    @GetMapping("/{bookId}")
    public List<ReviewDto> getReviews(@PathVariable("bookId") int bookId) {
        return reviewService.getReviewsByBookId(bookId);
    }

    @PostMapping("/add-review")
    public ResponseEntity<ReviewDto> addReviews(@Valid @RequestBody ReviewDto reviewDto, Principal principal) {
        ReviewDto reviewDto1 = reviewService.addReview(reviewDto, principal.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteReviews(@PathVariable("id") int id) {
        reviewService.deleteReview(id);
        return "Review id : " + id + " deleted";
    }
}
