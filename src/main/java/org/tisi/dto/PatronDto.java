package org.tisi.dto;

import lombok.Builder;
import lombok.NonNull;

import java.time.LocalDate;

@Builder
public record PatronDto(
        Long patronId,
        String patronName,
        @NonNull String email,
        String phoneNumber,
        LocalDate registrationDate
) {
}
