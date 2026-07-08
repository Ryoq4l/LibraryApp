package org.tisi.controller;

import lombok.RequiredArgsConstructor;
import org.tisi.dto.PatronDto;
import org.tisi.repository.PatronRepository;
import org.tisi.model.Patron;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor

public class PatronController {
    private final PatronRepository patronRepo;
    public List <PatronDto> getAllPatrons(){
return Arrays.asList(

);
    }
}
