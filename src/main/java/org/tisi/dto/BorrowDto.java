package org.tisi.dto;

import java.time.LocalDate;

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
) { }
