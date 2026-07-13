package org.tisi.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record BookDto(
        Long bookId,
        String title,
        String authorName,
        @NotNull Long authorId,
        String isbn,
        Integer publicationYear,
        Integer quantity,
        Integer availableQuantity
) {
}
