package org.tisi.controller;

import lombok.RequiredArgsConstructor;
import org.tisi.dto.BookDto;
import org.tisi.repository.BookRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepo;

    @GetMapping
    public List<BookDto> getAllBooks() {
        return Arrays.asList(
                new BookDto(1L, "Война и мир", "Лев Толстой", "978-5-17-118858-0", 1869, "Иван Петров (взял)"),
                new BookDto(2L, "Преступление и наказание", "Федор Достоевский", "978-5-04-115184-8", 1866, "Мария Смирнова (взяла)"),
                new BookDto(3L, "Мастер и Маргарита", "Михаил Булгаков", "978-5-17-088123-5", 1967, null),
                new BookDto(4L, "Анна Каренина", "Лев Толстой", "978-5-04-116734-4", 1877, "Иван Петров (взял)"),
                new BookDto(5L, "Идиот", "Федор Достоевский", "978-5-04-115185-5", 1868, "Алексей Иванов (взял)")
        );

    }
}

