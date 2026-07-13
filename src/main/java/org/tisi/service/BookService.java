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


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;
    private final PatronRepository patronRepo;
    private final BorrowRepository borrowRecordRepo;
    private final BookMapper bookMapper;

    public void createBook(BookDto bookDto) {

        Author author = authorRepo.findById(bookDto.authorId())
                .orElseThrow(() -> new RuntimeException("Author not found with id " + bookDto.authorId()));
        Set<Author> authors = new HashSet<>();
        authors.add(author);
        Book book = bookMapper.map(bookDto, authors);

        bookRepo.saveAndFlush(book);
    }

    public BookDto getBookById(Long id) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("book not found with id: " + id));
        return bookMapper.toDto(book);

    }
}

