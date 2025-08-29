package com.example.book_store_management.service;

import com.example.book_store_management.dto.AuthorWithBookDto;
import com.example.book_store_management.dto.AuthorDto;
import com.example.book_store_management.entity.Author;
import com.example.book_store_management.repository.AuthorRepo;
import com.example.book_store_management.mapper.CustomUtils;
import javax.persistence.EntityNotFoundException;

import lombok.NoArgsConstructor;
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

    public AuthorWithBookDto toAuthorAndBookDto(Author author) {
        return new AuthorWithBookDto(
                author.getAuthorId(),
                author.getName(),
                author.getGenre(),
                author.getBooks().stream().map(customUtils::toBooksOfAuthorDto).collect(Collectors.toList())
        );
    }

    public List<AuthorDto> getAuthor() {
        return authorRepo.findAll().stream().map(customUtils::toAuthorDto).collect(Collectors.toList());
    }

    public AuthorWithBookDto getAuthorById(int id) {
        return toAuthorAndBookDto(authorRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("no author id")));
    }

    public AuthorDto createAuthor(AuthorDto authorDto) {
        return customUtils.toAuthorDto(authorRepo.save(customUtils.toAuthorEntity(authorDto)));
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
