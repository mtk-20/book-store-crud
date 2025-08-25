package com.example.book_store_management.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class AuthorAndBookDto extends AuthorDto{

    private List<BookDto> bookDtos;

    public AuthorAndBookDto(int authorId, String name, String genre, List<BookDto> bookDtos) {
        super(authorId, name, genre);
        this.bookDtos = bookDtos;
    }
}
