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
    @GetMapping
    public List<PatronDto> getAllPatrons() {
        return Arrays.asList(
                new PatronDto(1L, "Иван Петров", "ivan@example.com", "+7-900-111-11-11", LocalDate.now().minusDays(30)),
                new PatronDto(2L, "Мария Смирнова", "maria@example.com", "+7-900-222-22-22", LocalDate.now().minusDays(15)),
                new PatronDto(3L, "Алексей Иванов", "alex@example.com", "+7-900-333-33-33", LocalDate.now().minusDays(5))
        );
    }
}

