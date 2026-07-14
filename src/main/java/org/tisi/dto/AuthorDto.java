package org.tisi.dto;

import lombok.Builder;

@Builder
public record AuthorDto(Long authorId, String authorName, String bio) {
}
