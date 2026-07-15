package org.tisi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
@Schema(
        name = "BookDto",
        description = "Data transfer object for book information"
)
public record BookDto(
        @Schema(
                description = "Unique identifier of the book",
                example = "1"
        )
        Long bookId,

        @NotBlank(message = "Title is required")
        @Schema(
                description = "Title of the book",
                example = "Harry Potter and the Philosopher's Stone"
        )
        String title,

        @Schema(
                description = "Name of the author (read-only, derived from authorId)",
                example = "J.K. Rowling",
                nullable = true
        )
        String authorName,

        @NotNull(message = "Author ID is required")
        @Min(value = 1, message = "Author ID must be positive")
        @Schema(
                description = "ID of the author who wrote the book",
                example = "1",
                minimum = "1"
        )
        Long authorId,

        @NotBlank(message = "ISBN is required")
        @Schema(
                description = "International Standard Book Number (ISBN)",
                example = "9780747532699"
        )
        String isbn,

        @Min(value = 1000, message = "Publication year must be after 1000")
        @Schema(
                description = "Year when the book was published",
                example = "1997",
                minimum = "0",
                maximum = "2026"
        )
        Integer publicationYear,

        @Min(value = 0, message = "Quantity cannot be negative")
        @Schema(
                description = "Total number of copies available in the library",
                example = "10",
                minimum = "0",
                defaultValue = "0"
        )
        Integer quantity,

        @Min(value = 0, message = "Available quantity cannot be negative")
        @Schema(
                description = "Number of copies currently available for borrowing",
                example = "8",
                minimum = "0",
                defaultValue = "0"
        )
        Integer availableQuantity
) {}