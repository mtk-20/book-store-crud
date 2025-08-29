package com.example.book_store_management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private int reviewId;
    private String comment;
    private int rating;
    private int bookId;
    private String userName;
}
