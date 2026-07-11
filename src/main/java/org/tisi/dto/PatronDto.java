package org.tisi.dto;

import java.time.LocalDate;

public record PatronDto(
        Long patronId,
        String patronName,
        String email,
        String phoneNumber,
        LocalDate registrationDate
) {}
