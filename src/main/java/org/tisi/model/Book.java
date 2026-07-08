package org.tisi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data

@NoArgsConstructor
@AllArgsConstructor

@Entity

@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookid")
    private long bookId;

    @Column(name = "title")
    private String title;

    @Column(name = "isbn", columnDefinition = "Char(13)", nullable = false, unique = true)
    private String isbn;

    @Column(name = "year")
    private short publicationYear;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "availableQuantity")
    private short availableQuantity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "AuthorToBook",
            joinColumns = @JoinColumn(name = "authorId"),
            inverseJoinColumns = @JoinColumn(name = "bookId")

    )
    private Set<Author> authors = new HashSet<>();
}
