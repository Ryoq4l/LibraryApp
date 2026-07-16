package org.tisi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tisi.dto.AuthorDto;
import org.tisi.exceptions.GenericException;
import org.tisi.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    //CREATE ENDPOINT
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAuthor(@RequestBody AuthorDto authorDto) {

        authorService.createAuthor(authorDto);
    }

    //READ ENDPOINTS
    @GetMapping
    public Page<AuthorDto> getAllAuthors(Pageable pageable) {
        return authorService.getAllAuthors(pageable);
    }

    @GetMapping("/{authorId}")
    public AuthorDto getAuthorById(@PathVariable Long authorId) {
        return authorService.getAuthorById(authorId);
    }

    //UPDATE ENDPOINT
    @PutMapping("/{authorId}")
    public AuthorDto updateAuthor(
            @PathVariable Long authorId,
            @RequestBody AuthorDto authorDto) {
        return authorService.updateAuthor(authorId, authorDto);
    }

    //DELETE ENDPOINT
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable Long authorId) {
        authorService.deleteAuthor(authorId);
    }

    @GetMapping("/by-name/{authorName}")
    public AuthorDto getAuthorByAuthorName(@PathVariable String authorName) {
        return authorService.getAuthorByAuthorName(authorName);
    }


}