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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowService {
    private final BorrowRepository borrowRepo;
    private final PatronRepository patronRepo;
    private final BookRepository bookRepo;
    private final BorrowMapper borrowMapper;

    //CREATE
    @Transactional
    public void createBorrowRecord(BorrowDto borrowDto) {
        Book book = bookRepo.findById(borrowDto.bookId()).orElseThrow();
        Patron patron = patronRepo.findById(borrowDto.patronId()).orElseThrow();
        if (book.getAvailableQuantity() <= 0) {
            throw new RuntimeException(
                    "No available copies of " + book.getTitle() + " at the moment"
            );
        }
        BorrowRecord borrowRecord = borrowMapper.map(borrowDto, book, patron);
        book.setAvailableQuantity(book.getAvailableQuantity() - 1);
        bookRepo.save(book);

        borrowRepo.save(borrowRecord);
    }

    //READ
    public List<BorrowDto> getBorrowRecordsByPatronId(Long patronId) {
        patronRepo.findById(patronId)
                .orElseThrow(() -> new RuntimeException("Patron not found with id: " + patronId));
        List<BorrowRecord> records = borrowRepo.findByPatronIdWithDetails(patronId);
        return records.stream()
                .map(borrowMapper::toDto)
                .collect(Collectors.toList());
    }
    public List<BorrowDto> getBorrowRecordsByPatronName(String patronName) {
        Patron patron = patronRepo.findByPatronName(patronName)
                .orElseThrow(() -> new RuntimeException("Patron not found with name: " + patronName));

        List<BorrowRecord> records = borrowRepo.findByPatronIdWithDetails(patron.getPatronId());
        return records.stream()
                .map(borrowMapper::toDto)
                .collect(Collectors.toList());
    }
    //UPDATE
    @Transactional
    public BorrowDto returnBook(Long borrowRecordId) {
        BorrowRecord borrowRecord = borrowRepo.findById(borrowRecordId)
                .orElseThrow(() -> new RuntimeException("Borrow record not found with id: " + borrowRecordId));

        if (borrowRecord.isReturned()) {
            throw new RuntimeException("Book has already been returned");
        }

        borrowRecord.setReturnDate(java.time.LocalDate.now());
        borrowRecord.setReturned(true);

        Book book = borrowRecord.getBook();
        book.setAvailableQuantity(book.getAvailableQuantity() + 1);
        bookRepo.save(book);

        BorrowRecord updatedRecord = borrowRepo.save(borrowRecord);
        return borrowMapper.toDto(updatedRecord);
    }

    //DELETE
    @Transactional
    public void deleteBorrowRecord(Long borrowRecordId) {
        BorrowRecord borrowRecord = borrowRepo.findById(borrowRecordId)
                .orElseThrow(() -> new RuntimeException("Borrow record not found with id: " + borrowRecordId));

        if (!borrowRecord.isReturned()) {
            throw new RuntimeException("Cannot delete an active borrow record. Please return the book first.");
        }

        borrowRepo.delete(borrowRecord);
    }

}
