package org.tisi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tisi.dto.PatronDto;
import org.tisi.service.PatronService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/patrons")
@RequiredArgsConstructor

public class PatronController {
    private final PatronService patronService;
//CREATE ENDPOINT
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPatron(@RequestBody PatronDto patronDto) {
        patronService.createPatron(patronDto);
    }
//READ ENDPOINTS
    @GetMapping
    public List<PatronDto> getAllPatrons(){
        return patronService.getAllPatrons();
    }
    @GetMapping("/id/{id}")
    public PatronDto getPatronByPatronId(@PathVariable Long patronId) {return patronService.getPatronByPatronId(patronId);}

    @GetMapping("/email/{email}")
    public PatronDto getPatronByEmail(@PathVariable String email) {
        return patronService.getPatronByEmail(email);
    }

    @GetMapping("/phone/{phoneNumber}")
    public PatronDto getPatronsByPhoneNumber(@PathVariable String phoneNumber) {return patronService.getPatronByPhoneNumber(phoneNumber);}

    @GetMapping("/patron-name/{patronName}")
    public PatronDto getPatronByPatronName(@PathVariable String patronName) {return patronService.getPatronByPatronName(patronName);}
    @GetMapping("/registration-range")
    public List<PatronDto> getPatronsByRegistrationRange(
            @RequestParam LocalDate start,
            @RequestParam LocalDate end
            ){
        return patronService.getPatronsByRegistrationRange(start, end);
    }
//UPDATE ENDPOINT
@PutMapping("{id}")
    public PatronDto updatePatron(
            @PathVariable Long id,
            @RequestBody PatronDto patronDto){
        return patronService.updatePatron(id, patronDto);
}
}

