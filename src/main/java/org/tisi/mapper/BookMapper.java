package org.tisi.mapper;

import org.springframework.stereotype.Component;
import org.tisi.dto.BookDto;
import org.tisi.model.Author;
import org.tisi.model.Book;

import java.util.Set;

@Component
public class BookMapper {
    public Book map(BookDto bookDto, Set<Author> authors){
        Integer quantity = bookDto.quantity() != null ? bookDto.quantity() : 0;
        Integer availableQuantity = bookDto.availableQuantity() != null ? bookDto.quantity() : quantity;
        return Book.builder()
                .quantity(quantity)
                .availableQuantity(availableQuantity)
                .isbn(bookDto.isbn())
                .title(bookDto.title())
                .authors(authors)
                .build();
    }
}
