package org.tisi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tisi.dto.BookDto;
import org.tisi.dto.criteria.BookSearchCriteria;
import org.tisi.service.BookService;
import org.tisi.service.BorrowService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Tag(name = "Book Management", description = "APIs for managing books")
public class BookController {
    private final BookService bookService;
    private final BorrowService borrowService;

    @Operation(
            summary = "Create a new book",
            description = "Adds a new book to the library system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input or author not found"),
            @ApiResponse(responseCode = "404", description = "Author not found")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@RequestBody BookDto bookDto) {
        bookService.createBook(bookDto);
    }

    @Operation(
            summary = "Get all books",
            description = "Retrieves a list of all books in the library"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
                    content = @Content(schema = @Schema(implementation = BookDto.class)))
    })
    @GetMapping
    public Page<BookDto> getAllBooks(Pageable pageable) {
        return bookService.getAllBooks(pageable);
    }

    @Operation(
            summary = "Get book by ID",
            description = "Retrieves a book by its unique identifier"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book found",
                    content = @Content(schema = @Schema(implementation = BookDto.class))),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping("/{bookId}")
    public BookDto getBookById(
            @Parameter(description = "ID of the book", required = true, example = "1")
            @PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }

    @Operation(
            summary = "Get books by author",
            description = "Retrieves all books written by a specific author"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books found",
                    content = @Content(schema = @Schema(implementation = BookDto.class))),
            @ApiResponse(responseCode = "404", description = "Author not found")
    })
    @GetMapping("/by-author/{authorId}")
    public List<BookDto> getBooksByAuthorId(
            @Parameter(description = "Author ID", required = true, example = "1")
            @PathVariable Long authorId) {
        return bookService.getBooksByAuthorId(authorId);
    }

    @Operation(
            summary = "Get books by publication year range",
            description = "Retrieves books published between two years (inclusive)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books found",
                    content = @Content(schema = @Schema(implementation = BookDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid year range")
    })
    @GetMapping("/publication-year-range")
    public List<BookDto> getBooksByPublicationYearRange(
            @Parameter(description = "Start year", required = true, example = "1990")
            @RequestParam int startY,
            @Parameter(description = "End year", required = true, example = "2024")
            @RequestParam int endY
    ) {
        return bookService.getBooksByPublicationRange(startY, endY);
    }

    @Operation(
            summary = "Search books with filters",
            description = "Advanced search for books using multiple optional criteria"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search results",
                    content = @Content(schema = @Schema(implementation = BookDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid search parameters")
    })
    @GetMapping("/search")
    public Page<BookDto> searchBooks(@Valid @ModelAttribute
                                     BookSearchCriteria criteria,
                                     Pageable pageable
    ) {
        return bookService.searchBooks(criteria, pageable);
    }

    @Operation(
            summary = "Update a book",
            description = "Updates an existing book's information"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book updated successfully",
                    content = @Content(schema = @Schema(implementation = BookDto.class))),
            @ApiResponse(responseCode = "404", description = "Book not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/{bookId}")
    public BookDto updateBook(
            @Parameter(description = "ID of the book to update", required = true, example = "1")
            @PathVariable Long bookId,
            @RequestBody BookDto bookDto) {
        return bookService.updateBook(bookId, bookDto);
    }

    @Operation(
            summary = "Delete a book",
            description = "Deletes a book. Only allowed if no active borrow records exist."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Cannot delete book with active borrows"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(
            @Parameter(description = "ID of the book to delete", required = true, example = "1")
            @PathVariable Long bookId) {
        bookService.deleteBook(bookId);
    }
}