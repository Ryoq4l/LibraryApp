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

        Long authorId,

        String authorName,

        String bio
) {}