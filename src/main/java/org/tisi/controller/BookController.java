package org.tisi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.tisi.service.BookService;
import org.springframework.web.bind.annotation.*;
import org.tisi.dto.BookDto;
import org.tisi.model.Book;
import org.tisi.service.BookService;

import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@RequestBody BookDto bookDto){
        return bookService.createBook(bookDto);
    }

}

