package org.tisi.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.tisi.dto.BorrowDto;
import org.tisi.mapper.BorrowMapper;
import org.tisi.model.Book;
import org.tisi.model.BorrowRecord;
import org.tisi.model.Patron;
import org.tisi.repository.BorrowRepository;
import org.tisi.repository.BookRepository;
import org.tisi.repository.PatronRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowService {
    private final BorrowRepository borrowRepo;
    private final PatronRepository patronRepo;
    private final BookRepository bookRepo;
    private final BorrowMapper borrowMapper;

    @Transactional
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
    public List<BorrowRecord> getBorrowRecordsByPatronId(Long patronId){
        patronRepo.findById(patronId)
                .orElseThrow(() -> new RuntimeException("No patron found"));
        return borrowRepo.findByPatronIdWithDetails(patronId);

    }
}
