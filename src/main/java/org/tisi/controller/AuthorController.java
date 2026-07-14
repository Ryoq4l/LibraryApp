package org.tisi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tisi.dto.AuthorDto;
import org.tisi.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAuthor(@RequestBody AuthorDto authorDto) {

        authorService.createAuthor(authorDto);
    }

    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public AuthorDto getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @PutMapping("/{id}")
    public AuthorDto updateAuthor(
            @PathVariable Long id,
            @RequestBody AuthorDto authorDto) {
        return authorService.updateAuthor(id, authorDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }

    @GetMapping("by-name/{authorName}")
    public AuthorDto getAuthorByAuthorName(@PathVariable String authorName) {
        return authorService.getAuthorByAuthorName(authorName);
    }
}