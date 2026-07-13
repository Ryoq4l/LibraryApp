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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBorrowRecord(@RequestBody BorrowDto borrowDto) {
        borrowService.createBorrowRecord(borrowDto);
    }
    @GetMapping("/patrons/{patronId}")
    public List<BorrowDto> getBorrowRecordsByPatronId(@PathVariable Long patronId){
        return borrowService.getBorrowRecordsByPatronId(patronId);
    }
}
