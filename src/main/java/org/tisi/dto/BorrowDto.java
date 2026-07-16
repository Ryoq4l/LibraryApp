package org.tisi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
@Builder
@Schema(name = "BorrowDto", description = "Data transfer object for book borrowing records")
public record BorrowDto(

        Long id,

        @NotNull(message = "Book ID is required")

        Long bookId,

        String bookTitle,

        String bookIsbn,

        Integer bookPublicationYear,

        Long patronId,

        String patronName,

        String patronEmail,

        String patronPhone,

        LocalDate borrowDate,

        LocalDate dueDate,

        LocalDate returnDate,

        boolean isReturned) {
}