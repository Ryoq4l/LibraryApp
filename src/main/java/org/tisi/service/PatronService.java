package org.tisi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tisi.dto.AuthorDto;
import org.tisi.dto.PatronDto;
import org.tisi.mapper.PatronMapper;
import org.tisi.model.Author;
import org.tisi.model.Patron;
import org.tisi.repository.PatronRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PatronService {
    private final PatronRepository patronRepo;
    private final PatronMapper patronMapper;

    public void createPatron(PatronDto patronDto) {
        if (patronRepo.existsByEmail(patronDto.email())) {
            throw new RuntimeException("Patron with email " + patronDto.email() + " already exists");
        }
        Patron patron = patronMapper.map(patronDto);
        patronRepo.saveAndFlush(patron);
    }

    public List<PatronDto> getAllPatrons() {
        return patronRepo.findAll().stream()
                .map(patronMapper::toDto)
                .collect(Collectors.toList());
    }
    public PatronDto getPatronByEmail(String email) {
        Patron patron = patronRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Patron not found with email: " + email));
        return patronMapper.toDto(patron);
    }
}
