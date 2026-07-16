package org.tisi.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.tisi.dto.BookDto;
import org.tisi.dto.criteria.BookSearchCriteria;
import org.tisi.exceptions.BusinessException;
import org.tisi.exceptions.ResourceNotFoundException;
import org.tisi.mapper.BookMapper;
import org.tisi.model.Book;
import org.tisi.model.Author;
import org.tisi.repository.BookRepository;
import org.tisi.repository.AuthorRepository;
import org.tisi.specification.BookSpecification;

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
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + bookDto.authorId()));
        Set<Author> authors = new HashSet<>();
        authors.add(author);
        Book book = bookMapper.map(bookDto, authors);

        bookRepo.saveAndFlush(book);
    }

    //READ
    public Page<BookDto> getAllBooks(Pageable pageable) {
        return bookRepo.findAll(pageable)
                .map(bookMapper::toDto);
    }

    public BookDto getBookById(Long bookId) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("book not found with id: " + bookId));
        return bookMapper.toDto(book);

    }

    public List<BookDto> getBooksByAuthorId(Long authorId) {
        return bookRepo.findDistinctByAuthors_AuthorId(authorId).stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> getBooksByPublicationRange(int startY, int endY) {
        return bookRepo.findByPublicationYearBetween(startY, endY).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    public Page<BookDto> searchBooks(BookSearchCriteria criteria, Pageable pageable) {
        if (criteria == null || criteria.isEmpty()) {
            return bookRepo.findAll(pageable).map(bookMapper::toDto);
        }
        Specification<Book> spec = BookSpecification.buildSpecification(criteria);
        return bookRepo.findAll(spec, pageable).map(bookMapper::toDto);
    }

    //UPDATE
    @Transactional
    public BookDto updateBook(Long bookId, BookDto bookDto) {
        Book existingBook = bookRepo.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        if (bookDto.title() != null) {
            existingBook.setTitle(bookDto.title());
        }
        if (bookDto.isbn() != null) {
            existingBook.setIsbn(bookDto.isbn());
        }
        if (bookDto.publicationYear() != null) {
            existingBook.setPublicationYear(bookDto.publicationYear());
        }
        if (bookDto.quantity() != null) {
            existingBook.setQuantity(bookDto.quantity());
            existingBook.setAvailableQuantity(bookDto.quantity());
        }

        if (bookDto.authorId() != null) {
            Author author = authorRepo.findById(bookDto.authorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + bookDto.authorId()));
            Set<Author> authors = new HashSet<>();
            authors.add(author);
            existingBook.setAuthors(authors);
        }

        Book updatedBook = bookRepo.saveAndFlush(existingBook);
        return bookMapper.toDto(updatedBook);
    }

    //DELETE
    @Transactional
    public void deleteBook(Long bookId) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        boolean hasActiveBorrows = book.getBorrowRecords() != null &&
                book.getBorrowRecords().stream()
                        .anyMatch(record -> !record.isReturned());

        if (hasActiveBorrows) {
            throw new BusinessException("Cannot delete book with active borrow records");
        }

        bookRepo.delete(book);

    }
}

