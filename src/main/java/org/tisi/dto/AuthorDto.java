package org.tisi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(
        name = "AuthorDto",
        description = "Data transfer object for author information"
)
public record AuthorDto(
        @Schema(
                description = "Unique identifier of the author",
                example = "1",
                accessMode = Schema.AccessMode.READ_ONLY
        )
        Long authorId,

        @NotBlank(message = "Author name is required")
        @Size(min = 2, max = 100, message = "Author name must be between 2 and 100 characters")
        @Schema(
                description = "Full name of the author",
                example = "J.K. Rowling",
                minLength = 2,
                maxLength = 100
        )
        String authorName,

        @Size(max = 500, message = "Biography cannot exceed 500 characters")
        @Schema(
                description = "Biography of the author",
                example = "British author, best known for the Harry Potter series",
                maxLength = 500,
                nullable = true
        )
        String bio
) {}