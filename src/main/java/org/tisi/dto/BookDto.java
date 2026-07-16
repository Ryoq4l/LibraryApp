package org.tisi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
@Schema(
        name = "BookDto",
        description = "Data transfer object for book information"
)
public record BookDto(
        Long bookId,

        @NotBlank(message = "Title is required")
        String title,

        String authorName,

        Long authorId,

        @NotBlank(message = "ISBN is required")

        String isbn,

        @Min(value = 1000, message = "Publication year must be after 1000")
        Integer publicationYear,

        @Min(value = 0, message = "Quantity cannot be negative")
        Integer quantity,

        @Min(value = 0, message = "Available quantity cannot be negative")
        Integer availableQuantity
) {}