package org.tisi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@Schema(
        name = "BorrowDto",
        description = "Data transfer object for book borrowing records"
)
public record BorrowDto(
        @Schema(
                description = "Unique identifier of the borrow record",
                example = "1"
        )
        Long id,

        @NotNull(message = "Book ID is required")
        @Schema(
                description = "ID of the book being borrowed",
                example = "1",
                minimum = "1"
        )
        Long bookId,

        @Schema(
                description = "Title of the borrowed book (read-only)",
                example = "Harry Potter and the Philosopher's Stone",
                nullable = true
        )
        String bookTitle,

        @Schema(
                description = "ISBN of the borrowed book (read-only)",
                example = "9780747532699",
                nullable = true
        )
        String bookIsbn,

        @Schema(
                description = "Publication year of the borrowed book (read-only)",
                example = "1997",
                nullable = true
        )
        Integer bookPublicationYear,

        @NotNull(message = "Patron ID is required")
        @Schema(
                description = "ID of the patron borrowing the book",
                example = "1",
                minimum = "1"
        )
        Long patronId,

        @Schema(
                description = "Name of the patron (read-only)",
                example = "John Doe",
                nullable = true
        )
        String patronName,

        @Schema(
                description = "Email of the patron (read-only)",
                example = "john.doe@example.com",
                nullable = true,
                format = "email"
        )
        String patronEmail,

        @Schema(
                description = "Phone number of the patron (read-only)",
                example = "+1-555-123-4567",
                nullable = true
        )
        String patronPhone,

        @PastOrPresent(message = "Borrow date cannot be in the future")
        @Schema(
                description = "Date when the book was borrowed",
                example = "2024-01-20",
                format = "date",
                defaultValue = "Current date"
        )
        LocalDate borrowDate,

        @Future(message = "Due date must be in the future")
        @Schema(
                description = "Date when the book is due for return",
                example = "2024-02-03",
                format = "date",
                defaultValue = "14 days after borrow date"
        )
        LocalDate dueDate,

        @Schema(
                description = "Date when the book was actually returned",
                example = "2024-02-01",
                format = "date",
                nullable = true
        )
        LocalDate returnDate,

        @Schema(
                description = "Flag indicating whether the book has been returned",
                example = "false",
                defaultValue = "false"
        )
        boolean isReturned
) {}