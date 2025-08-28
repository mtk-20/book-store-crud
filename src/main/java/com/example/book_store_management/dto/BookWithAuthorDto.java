package com.example.book_store_management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookWithAuthorDto {

    private int id;
    private String title;
    private float price;
    //private int authorId;

    private AuthorDto authorDto;
}
