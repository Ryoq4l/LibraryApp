package org.tisi.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PatronDto(
        Long patronId,
        String patronName,
        String email,
        String phoneNumber,
        LocalDate registrationDate
) {
}
