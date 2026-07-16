    package org.tisi.service;

    import jakarta.transaction.Transactional;
    import lombok.RequiredArgsConstructor;

    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.stereotype.Service;
    import org.tisi.dto.AuthorDto;
    import org.tisi.exceptions.BusinessException;
    import org.tisi.exceptions.ResourceNotFoundException;
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

        //CREATE
        public void createAuthor(AuthorDto authorDto) {
            Author author = authorMapper.map(authorDto);
            authorRepo.save(author);
        }

        //READ
        public AuthorDto getAuthorById(Long authorId) {
            Author author = authorRepo.findById(authorId)
                    .orElseThrow(() -> new RuntimeException("Author not found with id: " + authorId));
            return authorMapper.toDto(author);
        }

        public Page<AuthorDto> getAllAuthors(Pageable pageable) {
            return authorRepo.findAll(pageable)
                    .map(authorMapper::toDto);
        }

        @Transactional
        public AuthorDto getAuthorByAuthorName(String authorName) {
            Author author = authorRepo.findByAuthorName(authorName)
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found with name: " + authorName));
            return authorMapper.toDto(author);
        }

        //UPDATE
        @Transactional
        public AuthorDto updateAuthor(Long authorId, AuthorDto authorDto) {
            Author existingAuthor = authorRepo.findById(authorId)
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId));

            existingAuthor.setAuthorName(authorDto.authorName());
            existingAuthor.setBio(authorDto.bio());

            Author updatedAuthor = authorRepo.save(existingAuthor);
            return authorMapper.toDto(updatedAuthor);
        }

        //DELETE
        @Transactional
        public void deleteAuthor(Long authorId) {
            Author author = authorRepo.findById(authorId)
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId));

            if (author.getBooks() != null && !author.getBooks().isEmpty()) {
                throw new BusinessException("Cannot delete author with existing books. " +
                        "Remove books first or reassign to another author.");
            }

            authorRepo.delete(author);

        }
    }
