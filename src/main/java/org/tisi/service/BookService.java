package org.tisi.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tisi.dto.BookDto;
import org.tisi.model.Book;
import org.tisi.model.Author;
import org.tisi.model.Patron;
import org.tisi.repository.BookRepository;
import org.tisi.repository.AuthorRepository;
import org.tisi.repository.PatronRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;
    private final PatronRepository patronRepo;
    private final ModelMapper modelMapper;

    public BookDto createBook(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        book.setQuantity(bookDto.getQuantity() != null ? bookDto.getQuantity() : 0);
        book.setAvailableQuantity(bookDto.getAvailableQuantity() != null ? bookDto.getQuantity() : book.getQuantity());

        if (bookDto.getAuthorId() != null) {
            Author author = authorRepo.findById(bookDto.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("Author not found with id " + bookDto.getAuthorId()));
            Set<Author> authors = new HashSet<>();
            authors.add(author);
            book.setAuthors(authors);
        }
        Book saved = bookRepo.save(book);
        return modelMapper.map(saved, BookDto.class);


    }
}

