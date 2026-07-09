package org.tisi.controller;

import lombok.RequiredArgsConstructor;
import org.tisi.dto.PatronDto;
import org.tisi.repository.PatronRepository;
import org.tisi.model.Patron;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping ("/api/patrons")
@RequiredArgsConstructor

public class PatronController {
    private final PatronRepository patronRepo;

}

