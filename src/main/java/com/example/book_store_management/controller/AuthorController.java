package com.example.book_store_management.controller;

import com.example.book_store_management.dto.AuthorDto;
import com.example.book_store_management.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author")
    public List<AuthorDto> getAuthors() {
        return authorService.getAuthor();
    }

    @GetMapping("/author/{id}")
    public AuthorDto getAuthorById(@PathVariable("id") int id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping("/create-author")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDto createAuthors(@RequestBody AuthorDto authorDto) {
        return authorService.createAuthor(authorDto);
    }

    @PutMapping("/update-author/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AuthorDto updateAuthors(@PathVariable("id") int id, @RequestBody AuthorDto authorDto) {
        return authorService.updateAuthor(id, authorDto);
    }

}
