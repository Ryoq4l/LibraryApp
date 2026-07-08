package org.tisi.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorid")
    private Long authorId;

    @Column(name = "name")
    private String authorName;

    @Column(name = "bio")
    private String bio;

}
