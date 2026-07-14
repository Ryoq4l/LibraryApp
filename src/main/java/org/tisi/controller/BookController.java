package org.tisi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.tisi.dto.criteria.BookSearchCriteria;
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

    //CREATE ENDPOINT
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@RequestBody BookDto bookDto) {
        bookService.createBook(bookDto);
    }

    //READ ENDPOINTS
    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{bookId}")
    public BookDto getBookById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }
    @GetMapping("/by-author/{authorId}")
    public List<BookDto> getBooksByAuthorId(@PathVariable Long authorId) {
        return bookService.getBooksByAuthorId(authorId);
    }
    @GetMapping("/publication-year-range")
    public List<BookDto> getBooksByPublicationYearRange(
            @RequestParam int startY,
            @RequestParam int endY
    ) {
        return bookService.getBooksByPublicationRange(startY, endY);
    }
    @GetMapping("/search")
    public List<BookDto> searchBooks(@Valid @ModelAttribute BookSearchCriteria criteria) {
        return bookService.searchBooks(criteria);
    }

//UPDATE ENDPOINT
@PutMapping("/{bookId}")
public BookDto updateBook(
        @PathVariable Long bookId,
        @RequestBody BookDto bookDto) {
    return bookService.updateBook(bookId, bookDto);
}
//DELETE ENDPOINT
@DeleteMapping("/{bookId}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deleteBook(@PathVariable Long bookId) {
    bookService.deleteBook(bookId);
}
}

