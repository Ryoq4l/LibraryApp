package org.tisi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

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
}
