package com.example.book_store_management.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class AuthorWithBookDto extends AuthorDto{

    private List<BooksOfAuthorDto> booksOfAuthorDtos;

    public AuthorWithBookDto(int authorId, String name, String genre, List<BooksOfAuthorDto> booksOfAuthorDtos) {
        super(authorId, name, genre);
        this.booksOfAuthorDtos = booksOfAuthorDtos;
    }
}
