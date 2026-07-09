package org.tisi.controller;

import lombok.RequiredArgsConstructor;
import org.tisi.dto.AuthorDto;
import org.tisi.repository.AuthorRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorRepository authorRepo;

    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        return Arrays.asList(
                new AuthorDto( 1L, "Лев Толстой", "Великий русский писатель"),
                new AuthorDto(2L, "Федор Достоевский", "Русский писатель и мыслитель"),
                new AuthorDto(3L, "Михаил Булгаков", "Русский писатель и драматург")
        );
    }
}
