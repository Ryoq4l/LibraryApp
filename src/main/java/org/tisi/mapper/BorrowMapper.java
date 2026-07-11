package org.tisi.mapper;

import org.springframework.stereotype.Component;
import org.tisi.dto.BorrowDto;
import org.tisi.model.Book;
import org.tisi.model.BorrowRecord;
import org.tisi.model.Patron;

import java.time.LocalDate;

@Component
public class BorrowMapper {
    public BorrowRecord map (Book book, Patron patron) {
        return BorrowRecord.builder()
                .patron(patron)
                .book(book)
                .borrowDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(14))
                .returnDate(null)
                .isReturned(false)
                .build();
    }
}
