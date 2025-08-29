package com.example.book_store_management.dto;

import com.example.book_store_management.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookWithReviewDto {

    private int id;
    private String title;
    private float price;
    private List<ReviewDto> reviewDto;
}
