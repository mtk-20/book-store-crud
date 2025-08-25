package com.example.book_store_management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookDto {

    private int id;
    private String title;
    private float price;
    private int authorId;

}
