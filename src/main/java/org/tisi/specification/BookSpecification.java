package org.tisi.specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.tisi.dto.criteria.BookSearchCriteria;
import org.tisi.model.Book;

@Component
public class BookSpecification {
    public static Specification<Book> buildSpecification(BookSearchCriteria criteria) {
        return Specification
                .where(hasTitle(criteria.title()))
                .and(hasAuthor(criteria.authorName()));
                //.and(hasIsbn(criteria.isbn()))
                //.and(isAvailable(criteria.available()))
                //.and(YearRange(criteria.yearFrom(), criteria.yearTo()))
                //.and(borrowedByPatron(criteria.patronId()))
                //.and(borrowedRange(criteria.borrowedFrom(), criteria.borroweTo()));
    }

    //регистронезависимый поиск книги по названию:
    public static Specification<Book> hasTitle(String title) {
        return (root, query, cb) -> {
            if (title == null || title.trim().isEmpty()) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase().trim() + "%");
        };

    }
    //регистронезависимый поиск по автору через join
    public static Specification<Book> hasAuthor(String authorName) {
        return (root, query, cb) -> {
            if (authorName == null || authorName.trim().isEmpty()) {
                return cb.conjunction();
            }
            query.distinct(true);

            Join<Object, Object> authorsJoin = root.join("authors", JoinType.LEFT);
            return cb.like(
                    cb.lower(authorsJoin.get("authorName")),
                    "%" + authorName.toLowerCase().trim() + "%"
            );
        };
    }
}
