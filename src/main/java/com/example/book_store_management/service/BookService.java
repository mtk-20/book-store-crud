package com.example.book_store_management.service;

import com.example.book_store_management.dto.BookDto;
import com.example.book_store_management.entity.Author;
import com.example.book_store_management.entity.Book;
import com.example.book_store_management.repository.AuthorRepo;
import com.example.book_store_management.repository.BookRepo;
import com.example.book_store_management.utils.CustomUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final CustomUtils customUtils;
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;

    public List<BookDto> getAllBook() {
        return bookRepo.findAll().stream().map(customUtils::toBookDto).collect(Collectors.toList());
    }

    public BookDto getBookById(int id) {
        return customUtils.toBookDto(bookRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("no book id")));
    }

    @Transactional
    public BookDto createBook(BookDto bookDto) {
        Author authorID = authorRepo.findById(bookDto.getAuthorId()).orElseThrow(() -> new EntityNotFoundException("There is no author."));
        Book savedBook = bookRepo.save(customUtils.toBookEntity(bookDto, authorID));
        return customUtils.toBookDto(savedBook);
    }

    @Transactional
    public BookDto updateBook(int id, BookDto bookDto) {
        Book existBooks = bookRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("No such book"));
        Author existAuthor = authorRepo.findById(bookDto.getAuthorId()).orElseThrow(() -> new IllegalStateException("No such author"));

        existBooks.setTitle(bookDto.getTitle());
        existBooks.setPrice(bookDto.getPrice());
        existBooks.setAuthor(existAuthor);
        return customUtils.toBookDto(bookRepo.save(existBooks));
    }

    @Transactional
    public void deleteBook(int id) {
        if (!bookRepo.existsById(id)) {
            throw new EntityNotFoundException("No book id");
        }
        bookRepo.deleteById(id);
    }
}
