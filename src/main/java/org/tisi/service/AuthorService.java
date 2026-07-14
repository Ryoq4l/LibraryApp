package org.tisi.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.tisi.dto.AuthorDto;
import org.tisi.mapper.AuthorMapper;
import org.tisi.model.Author;
import org.tisi.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<AuthorDto> getAllAuthors() {
        return authorRepo.findAll().stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());

    }
    @Transactional
    public AuthorDto getAuthorByAuthorName(String authorName) {
        Author author = authorRepo.findByAuthorName(authorName)
                .orElseThrow(() -> new RuntimeException("Author not found with name: " + authorName));
        return authorMapper.toDto(author);
    }

    @Transactional
    public AuthorDto updateAuthor(Long id, AuthorDto authorDto) {
        Author existingAuthor = authorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));

        existingAuthor.setAuthorName(authorDto.authorName());
        existingAuthor.setBio(authorDto.bio());

        Author updatedAuthor = authorRepo.save(existingAuthor);
        return authorMapper.toDto(updatedAuthor);
    }

    @Transactional
    public void deleteAuthor(Long id) {
        Author author = authorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));

        if (author.getBooks() != null && !author.getBooks().isEmpty()) {
            throw new RuntimeException("Cannot delete author with existing books. " +
                    "Remove books first or reassign to another author.");
        }

        authorRepo.delete(author);

    }
}
