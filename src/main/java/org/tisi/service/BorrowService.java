package org.tisi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tisi.dto.BorrowDto;
import org.tisi.mapper.BorrowMapper;
import org.tisi.model.Book;
import org.tisi.model.BorrowRecord;
import org.tisi.model.Patron;
import org.tisi.repository.BorrowRepository;
import org.tisi.repository.BookRepository;
import org.tisi.repository.PatronRepository;

@Service
@RequiredArgsConstructor
public class BorrowService {
    private final BorrowRepository borrowRepo;
    private PatronRepository patronRepo;
    private final BookRepository bookRepo;
    private final BorrowMapper borrowMapper;


    public void createBorrowRecord(BorrowDto borrowDto) {
        Book book = bookRepo.findById(borrowDto.bookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        Patron patron = patronRepo.findById(borrowDto.patronId())
                .orElseThrow(() -> new RuntimeException("Patron not found"));
        if (book.getAvailableQuantity() == null || book.getAvailableQuantity() <= 0) {
            throw new RuntimeException(
                    "No available copies of " + book.getTitle() + " at the moment"
            );
        }
        BorrowRecord borrowRecord = borrowMapper.map(book, patron);
        book.setAvailableQuantity(book.getAvailableQuantity() - 1);
        bookRepo.save(book);

        borrowRepo.save(borrowRecord);
    }
}
