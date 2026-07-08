package org.tisi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatronDto {
    private Long patronId;
    private String patronName;
    private String email;
    private String phoneNumber;
    private LocalDate registrationDate;
}
