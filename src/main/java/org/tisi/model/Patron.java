package org.tisi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "patron")
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patronid")
    private Long patronId;

    @Column(name = "name")
    private String patronName;

    @Column(name = "email")
    private String email;

    @Column (name = "phone")
    private String phoneNumber;

    @Column (name = "registrartionDate")
    private LocalDate registrationDate;

}
