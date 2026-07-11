package org.tisi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tisi.service.BorrowService;
import org.tisi.dto.BorrowDto;

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
}
