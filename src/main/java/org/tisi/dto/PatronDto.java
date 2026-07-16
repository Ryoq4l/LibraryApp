package org.tisi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@Schema(
        name = "PatronDto",
        description = "Data transfer object for library patron/member information"
)
public record PatronDto(

        Long patronId,

        String patronName,

        @NotBlank(message = "Email is required")
        String email,

        String phoneNumber,

        LocalDate registrationDate
) {}