package org.tisi.mapper;

import org.springframework.stereotype.Component;
import org.tisi.dto.AuthorDto;
import org.tisi.model.Author;

@Component
public class AuthorMapper {
    public Author map(AuthorDto authorDto) {
        if (authorDto == null) return null;
        return Author.builder()
                .authorName(authorDto.authorName())
                .bio(authorDto.bio())
                .build();

    }

    public AuthorDto toDto(Author entity) {
        return AuthorDto.builder()
                .authorId(entity.getAuthorId())
                .authorName(entity.getAuthorName())
                .bio(entity.getBio())
                .build();
    }


}
