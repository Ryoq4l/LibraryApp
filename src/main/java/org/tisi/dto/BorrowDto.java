package org.tisi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowDto {

    private Long id;

    private Long bookId;
    private String bookTitle;
    private String bookIsbn;
    private Integer bookPublicationYear;

    private Long patronId;
    private String patronName;
    private String patronEmail;
    private String patronPhone;

    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    private boolean isReturned;
}