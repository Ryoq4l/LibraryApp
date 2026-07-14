package org.tisi.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tisi.dto.AuthorDto;
import org.tisi.dto.PatronDto;
import org.tisi.mapper.PatronMapper;
import org.tisi.model.Author;
import org.tisi.model.Patron;
import org.tisi.repository.PatronRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PatronService {
    private final PatronRepository patronRepo;
    private final PatronMapper patronMapper;

    //CREATE
    public void createPatron(PatronDto patronDto) {
        if (patronRepo.existsByEmail(patronDto.email())) {
            throw new RuntimeException("Patron with email " + patronDto.email() + " already exists");
        }
        Patron patron = patronMapper.map(patronDto);
        patronRepo.saveAndFlush(patron);
    }

    //READ
    public List<PatronDto> getAllPatrons() {
        return patronRepo.findAll().stream()
                .map(patronMapper::toDto)
                .collect(Collectors.toList());
    }

    public PatronDto getPatronByPatronId(Long patronId) {
        Patron patron = patronRepo.findById(patronId)
                .orElseThrow(() -> new RuntimeException("Patron not found with id: " + patronId));
        return patronMapper.toDto(patron);
    }

    public PatronDto getPatronByEmail(String email) {
        Patron patron = patronRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Patron not found with email: " + email));
        return patronMapper.toDto(patron);
    }

    public PatronDto getPatronByPhoneNumber(String phoneNumber) {
        Patron patron = patronRepo.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("Patron not found with number: " + phoneNumber));
        return patronMapper.toDto(patron);
    }

    public PatronDto getPatronByPatronName(String patronName) {
        Patron patron = patronRepo.findByPatronName(patronName)
                .orElseThrow(() -> new RuntimeException("Patron not found with name: " + patronName));
        return patronMapper.toDto(patron);
    }

    public List<PatronDto> getPatronsByRegistrationRange(LocalDate start, LocalDate end) {
        return patronRepo.getPatronsByRegistrationBetween(start, end).stream()
                .map(patronMapper::toDto)
                .collect(Collectors.toList());
    }

    //UPDATE
    @Transactional
    public PatronDto updatePatron(Long patronId, PatronDto patronDto) {
        Patron existingPatron = patronRepo.findById(patronId)
                .orElseThrow(() -> new RuntimeException("Patron not found with id: " + patronId));

        existingPatron.setPatronName(patronDto.patronName());
        existingPatron.setEmail(patronDto.email());
        existingPatron.setPhoneNumber(patronDto.phoneNumber());

        Patron updatedPatron = patronRepo.saveAndFlush(existingPatron);
        return patronMapper.toDto(updatedPatron);
    }

    //DELETE
    @Transactional
    public void deletePatron(Long patronId) {
        Patron patron = patronRepo.findById(patronId)
                .orElseThrow(() -> new RuntimeException("Patron not found with id: " + patronId));
        if (patron.getBorrowRecords() == null && patron.getBorrowRecords().isEmpty()) {
            throw new RuntimeException("Cannot delete user with borrow records");
        }
        patronRepo.delete(patron);
    }
}
