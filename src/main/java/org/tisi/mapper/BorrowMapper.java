package org.tisi.mapper;

import org.springframework.stereotype.Component;
import org.tisi.dto.BorrowDto;
import org.tisi.model.Book;
import org.tisi.model.BorrowRecord;
import org.tisi.model.Patron;

import java.time.LocalDate;

@Component
public class BorrowMapper {
    public BorrowRecord map(BorrowDto dto, Book book, Patron patron) {
        if (dto == null) return null;

        LocalDate borrowDate = dto.borrowDate() != null ? dto.borrowDate() : LocalDate.now();
        LocalDate dueDate = dto.dueDate() != null ? dto.dueDate() : borrowDate.plusDays(14);

        return BorrowRecord.builder()
                .patron(patron)
                .book(book)
                .borrowDate(borrowDate)
                .dueDate(dueDate)
                .returnDate(null)
                .isReturned(false)
                .build();
    }

    public BorrowDto toDto(BorrowRecord entity) {
        if (entity == null) return null;
        return BorrowDto.builder()
                .id(entity.getId())
                .bookId(getBookId(entity))
                .bookTitle(getTitle(entity))
                .bookIsbn(getIsbn(entity))
                .bookPublicationYear(getYear(entity))
                .patronId(entity.getId())
                .patronName(getPatronName(entity))
                .patronEmail(getEmail(entity))
                .patronPhone(getPhone(entity))
                .borrowDate(entity.getBorrowDate())
                .dueDate(entity.getDueDate())
                .returnDate(entity.getReturnDate())
                .isReturned(entity.isReturned())

                .build();
    }

    private Long getBookId(BorrowRecord entity) {
        return entity.getBook() != null ? entity.getBook().getBookId() : null;
    }

    private String getTitle(BorrowRecord entity) {
        return entity.getBook() != null ? entity.getBook().getTitle() : null;
    }

    private String getIsbn(BorrowRecord entity) {
        return entity.getBook() != null ? entity.getBook().getIsbn() : null;
    }

    private Integer getYear(BorrowRecord entity) {
        return entity.getBook() != null ? entity.getBook().getPublicationYear() : null;
    }

    private Long getPatronId(BorrowRecord entity) {
        return entity.getPatron() != null ? entity.getPatron().getPatronId() : null;
    }

    private String getPatronName(BorrowRecord entity) {
        return entity.getPatron() != null ? entity.getPatron().getPatronName() : null;
    }

    private String getEmail(BorrowRecord entity) {
        return entity.getPatron() != null ? entity.getPatron().getEmail() : null;
    }

    private String getPhone(BorrowRecord entity) {
        return entity.getPatron() != null ? entity.getPatron().getPhoneNumber() : null;
    }


}
