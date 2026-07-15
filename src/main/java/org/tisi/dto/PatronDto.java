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
        @Schema(
                description = "Unique identifier of the patron",
                example = "1"
        )
        Long patronId,

        @NotBlank(message = "Patron name is required")
        @Size(min = 2, max = 100, message = "Patron name must be between 2 and 100 characters")
        @Schema(
                description = "Full name of the patron",
                example = "John Doe",
                minLength = 2,
                maxLength = 100
        )
        String patronName,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Schema(
                description = "Email address of the patron",
                example = "six.seven@example.com",
                format = "email"
        )
        String email,

        @Schema(
                description = "Contact phone number",
                example = "+1-555-123-4567",
                nullable = true
        )
        String phoneNumber,

        @Schema(
                description = "Date when the patron registered",
                example = "2024-01-15",
                format = "date",
                nullable = true
        )
        LocalDate registrationDate
) {}