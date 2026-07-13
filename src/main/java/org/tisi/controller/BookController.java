package org.tisi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.tisi.model.BorrowRecord;
import org.tisi.model.Patron;
import org.tisi.service.BookService;
import org.springframework.web.bind.annotation.*;
import org.tisi.dto.BookDto;
import org.tisi.service.BorrowService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BorrowService borrowService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@RequestBody BookDto bookDto) {
        bookService.createBook(bookDto);
    }
    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }
    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }
    @GetMapping("/search")
    public List<BookDto> searchBooksByTitle(@RequestParam String title) {
        return bookService.searchBooksByTitle(title);
    }
}

