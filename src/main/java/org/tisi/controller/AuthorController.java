package org.tisi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.tisi.repository.AuthorRepository;
import org.tisi.model.Author;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tisi.repository.BookRepository;

import java.util.List;
@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorRepository authorRepo;

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }
}
