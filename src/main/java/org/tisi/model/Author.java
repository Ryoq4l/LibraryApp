package org.tisi.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Data
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorId")
    private Long authorId;

    @Column(name = "name")
    private String authorName;

    @Column(name = "bio")
    private String bio;
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;
}
