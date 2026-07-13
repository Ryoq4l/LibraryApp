package org.tisi.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.tisi.dto.AuthorDto;
import org.tisi.mapper.AuthorMapper;
import org.tisi.model.Author;
import org.tisi.repository.AuthorRepository;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepo;
    private final AuthorMapper authorMapper;

    public void createAuthor(AuthorDto authorDto) {
        Author author = authorMapper.map(authorDto);
        authorRepo.save(author);
    }

    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
        return authorMapper.toDto(author);
    }
}
