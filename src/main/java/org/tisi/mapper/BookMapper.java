package org.tisi.mapper;

import org.springframework.stereotype.Component;
import org.tisi.dto.BookDto;
import org.tisi.model.Author;
import org.tisi.model.Book;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

@Component
public class BookMapper {
    public Book map(BookDto bookDto, Set<Author> authors) {
        Integer quantity = bookDto.quantity() != null ? bookDto.quantity() : 0;
        Integer availableQuantity = bookDto.availableQuantity() != null ? bookDto.quantity() : quantity;
        return Book.builder()
                .quantity(quantity)
                .availableQuantity(availableQuantity)
                .isbn(bookDto.isbn())
                .title(bookDto.title())
                .publicationYear(bookDto.publicationYear())
                .authors(authors)
                .build();
    }

    public BookDto toDto(Book entity) {
        if (entity == null) return null;
        return BookDto.builder()
                .bookId(entity.getBookId())
                .title(entity.getTitle())
                .publicationYear(entity.getPublicationYear())
                .authorId(getAuthorId(entity))
                .authorName(getAuthorName(entity))
                .isbn(entity.getIsbn())
                .quantity(entity.getQuantity())
                .availableQuantity(entity.getAvailableQuantity())
                .build();
    }

    private String getAuthorName(Book entity) {
        if (entity.getAuthors() == null || entity.getAuthors().isEmpty()) {
            return null;
        }
        return entity.getAuthors().stream()
                .map(Author::getAuthorName)
                .collect(Collectors.joining(", "));
    }

    private Long getAuthorId(Book entity) {
        if (entity.getAuthors() == null || entity.getAuthors().isEmpty()) {
            return null;
        }
        return entity.getAuthors().iterator().next().getAuthorId();
    }
}
