package org.tisi.mapper;

import org.springframework.stereotype.Component;
import org.tisi.dto.AuthorDto;
import org.tisi.model.Author;

@Component
public class AuthorMapper {
    public Author map(AuthorDto authorDto){
        return Author.builder()
                .authorName(authorDto.authorName())
                .bio(authorDto.bio())
                .build();

    }


}
