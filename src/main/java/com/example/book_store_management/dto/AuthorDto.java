package com.example.book_store_management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthorDto {

    private int authorId;
    private String name;
    private String genre;
}
