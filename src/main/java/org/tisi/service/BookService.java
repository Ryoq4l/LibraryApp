package org.tisi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tisi.dto.BookDto;
import org.tisi.mapper.BookMapper;
import org.tisi.model.Book;
import org.tisi.model.Author;
import org.tisi.repository.BookRepository;
import org.tisi.repository.AuthorRepository;
import org.tisi.repository.BorrowRepository;
import org.tisi.repository.PatronRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;
    private final BookMapper bookMapper;

    //CREATE
    public void createBook(BookDto bookDto) {

        Author author = authorRepo.findById(bookDto.authorId())
                .orElseThrow(() -> new RuntimeException("Author not found with id " + bookDto.authorId()));
        Set<Author> authors = new HashSet<>();
        authors.add(author);
        Book book = bookMapper.map(bookDto, authors);

        bookRepo.saveAndFlush(book);
    }

    //READ
    public List<BookDto> getAllBooks() {
        return bookRepo.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDto getBookById(Long bookId) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("book not found with id: " + bookId));
        return bookMapper.toDto(book);

    }

    public List<BookDto> searchBooksByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return getAllBooks();
        }

        return bookRepo.findByTitleContainingIgnoreCase(title).stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }
    public List<BookDto> getBooksByAuthorId (Long authorId){
        return bookRepo.findDistinctByAuthorId(authorId).stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }
    //UPDATE
    //DELETE
}

