package org.tisi.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BorrowDto(
        Long id,
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
        boolean isReturned
) {
}
