package org.tisi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tisi.model.BorrowRecord;
import org.tisi.service.BorrowService;
import org.tisi.dto.BorrowDto;

import java.util.List;

@RestController
@RequestMapping("/api/borrowRecords")
@RequiredArgsConstructor
public class BorrowController {
    private final BorrowService borrowService;

    //CREATE ENDPOINT
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBorrowRecord(@RequestBody BorrowDto borrowDto) {
        borrowService.createBorrowRecord(borrowDto);
    }

    //READ ENDPOINTS
    @GetMapping("/patrons/{patronId}")
    public List<BorrowDto> getBorrowRecordsByPatronId(@PathVariable Long patronId) {
        return borrowService.getBorrowRecordsByPatronId(patronId);
    }
    @GetMapping("/patrons/name/{patronName}")
    public List<BorrowDto> getBorrowRecordsByPatronName(@PathVariable String patronName) {
        return borrowService.getBorrowRecordsByPatronName(patronName);
    }
    //UPDATE ENDPOINT
    @PutMapping("/return/{borrowRecordId}")
    public BorrowDto returnBook(@PathVariable Long borrowRecordId) {
        return borrowService.returnBook(borrowRecordId);
    }

    //DELETE ENDPOINT
    @DeleteMapping("/{borrowRecordId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBorrowRecord(@PathVariable Long borrowRecordId) {
        borrowService.deleteBorrowRecord(borrowRecordId);
    }
}
