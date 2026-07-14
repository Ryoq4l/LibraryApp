package org.tisi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tisi.dto.PatronDto;
import org.tisi.service.PatronService;

@RestController
@RequestMapping("/api/patrons")
@RequiredArgsConstructor

public class PatronController {
    private final PatronService patronService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPatron(@RequestBody PatronDto patronDto) {
        patronService.createPatron(patronDto);
    }
    @GetMapping("/email/{email}")
    public PatronDto getPatronByEmail(@PathVariable String email) {
        return patronService.getPatronByEmail(email);
    }


}

