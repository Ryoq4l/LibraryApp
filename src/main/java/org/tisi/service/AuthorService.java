package org.tisi.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.tisi.dto.AuthorDto;
import org.tisi.model.Author;
import org.tisi.repository.AuthorRepository;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepo;
    private final ModelMapper modelMapper;

    public AuthorDto createAuthor(AuthorDto authorDto){
        Author author = modelMapper.map(authorDto, Author.class);
        Author saved = authorRepo.save(author);
        return modelMapper.map(saved, AuthorDto.class);
    }
}
