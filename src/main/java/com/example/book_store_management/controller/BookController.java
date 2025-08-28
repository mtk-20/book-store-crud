package com.example.book_store_management.controller;

import com.example.book_store_management.dto.BookDto;
import com.example.book_store_management.dto.BookWithAuthorDto;
import com.example.book_store_management.service.BookService;
import com.example.book_store_management.mapper.CustomUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;
    private final CustomUtils customUtils;

    @GetMapping()
    public List<BookDto> getBooks(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  @RequestParam(required = false) String search) {
        return bookService.getAllBook(page, size, search);
    }

    @GetMapping("{id}")
    public BookWithAuthorDto getBooksById(@PathVariable("id") int id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/create-book")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBooks(@RequestBody BookDto bookDto) {
        return bookService.createBook(bookDto);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BookDto updateBooks(@PathVariable("id") int id, @RequestBody BookDto bookDto) {
        return bookService.updateBook(id, bookDto);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBooks(@PathVariable("id") int id) {
        bookService.deleteBook(id);
    }

}
