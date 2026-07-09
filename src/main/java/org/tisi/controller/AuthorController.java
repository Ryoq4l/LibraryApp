package org.tisi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tisi.dto.AuthorDto;
import org.tisi.service.AuthorService;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.createAuthor(authorDto);
    }
}