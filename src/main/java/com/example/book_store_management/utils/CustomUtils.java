package com.example.book_store_management.utils;

import com.example.book_store_management.dto.AuthorDto;
import com.example.book_store_management.dto.BookDto;
import com.example.book_store_management.entity.Author;
import com.example.book_store_management.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class CustomUtils {

    public AuthorDto toAuthorDto(Author author) {
        return new AuthorDto(
                author.getAuthorId(),
                author.getName(),
                author.getGenre()
        );
    }

    public Author toAuthorEntity(AuthorDto authorDto) {
        Author author = new Author();
        author.setAuthorId(authorDto.getAuthorId());
        author.setName(authorDto.getName());
        author.setGenre(authorDto.getGenre());
        return author;
    }

    public BookDto toBookDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getPrice(),
                book.getAuthor().getAuthorId()
        );
    }

    public Book toBookEntity(BookDto bookDto, Author author) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setPrice(bookDto.getPrice());
        book.setAuthor(author);
        return book;
    }


}
