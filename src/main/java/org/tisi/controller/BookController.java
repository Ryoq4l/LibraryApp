package org.tisi.controller;

import lombok.RequiredArgsConstructor;
import org.tisi.repository.BookRepository;
import org.tisi.model.Book;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepo;

    @GetMapping
    public List<Book> getAllBooks() {

        return bookRepo.findAll();
    }

}

