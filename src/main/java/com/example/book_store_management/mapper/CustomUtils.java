package com.example.book_store_management.mapper;

import com.example.book_store_management.dto.*;
import com.example.book_store_management.entity.Author;
import com.example.book_store_management.entity.Book;
import com.example.book_store_management.entity.Review;
import com.example.book_store_management.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CustomUtils {

    // author
    public Author toAuthorEntity(AuthorDto authorDto) {
        Author author = new Author();
        author.setAuthorId(authorDto.getAuthorId());
        author.setName(authorDto.getName());
        author.setGenre(authorDto.getGenre());
        return author;
    }

    public AuthorDto toAuthorDto(Author author) {
        return new AuthorDto(
                author.getAuthorId(),
                author.getName(),
                author.getGenre()
        );
    }

    // book
    public BooksOfAuthorDto toBooksOfAuthorDto(Book book) {
        return new BooksOfAuthorDto(book.getId(), book.getTitle(), book.getPrice());
    }

    public BookWithAuthorDto toBookWithAuthorDto(Book book) {
        AuthorDto authorDto = new AuthorDto(
                book.getAuthor().getAuthorId(),
                book.getAuthor().getName(),
                book.getAuthor().getGenre()
        );
        return new BookWithAuthorDto(book.getId(), book.getTitle(), book.getPrice(), authorDto);
    }

    public Book toBookEntity(BookDto bookDto, Author author) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setPrice(bookDto.getPrice());
        book.setAuthor(author);
        return book;
    }

    public BookDto toBookDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getPrice(),
                book.getAuthor().getAuthorId()
        );
    }

    // review
    public Review toReviewEntity(ReviewDto reviewDto, Book book) {
        Review review = new Review();
        review.setReviewId(reviewDto.getReviewId());
        review.setComment(reviewDto.getComment());
        review.setRating(reviewDto.getRating());
        review.setBook(book);
        return review;
    }

    public ReviewDto toReviewDto(Review review) {
        return new ReviewDto(
                review.getReviewId(),
                review.getComment(),
                review.getRating(),
                review.getBook().getId(),
                review.getUserName()
        );

    }

    // user
    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getUserName(),
                user.getEmail()
        );
    }
}