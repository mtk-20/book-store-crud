package com.example.book_store_management.service;

import com.example.book_store_management.controller.BookController;
import com.example.book_store_management.dto.AuthorAndBookDto;
import com.example.book_store_management.dto.AuthorDto;
import com.example.book_store_management.entity.Author;
import com.example.book_store_management.repository.AuthorRepo;
import com.example.book_store_management.mapper.CustomUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final CustomUtils customUtils;
    private final AuthorRepo authorRepo;
    private final BookController bookController;

    public AuthorDto createAuthor(AuthorDto authorDto) {
        return customUtils.toAuthorDto(authorRepo.save(customUtils.toAuthorEntity(authorDto)));
    }

    public AuthorAndBookDto getAuthorById(int id) {
        return bookController.toAuthorAndBookDto(authorRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("no author id")));
    }

    public List<AuthorDto> getAuthor() {
        return authorRepo.findAll().stream().map(customUtils::toAuthorDto).collect(Collectors.toUnmodifiableList());
    }

    //for more
    @Transactional
    public AuthorDto updateAuthor(int id, AuthorDto authorDto) {
        Author existingAuthor = authorRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with ID: " + id));
        existingAuthor.setName(authorDto.getName());
        existingAuthor.setGenre(authorDto.getGenre());
        Author updatedAuthor = authorRepo.save(existingAuthor);
        return customUtils.toAuthorDto(updatedAuthor);
    }
}
