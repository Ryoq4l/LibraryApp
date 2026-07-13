package org.tisi.mapper;

import org.springframework.stereotype.Component;
import org.tisi.dto.PatronDto;
import org.tisi.model.Patron;

@Component
public class PatronMapper {
    public Patron map(PatronDto patronDto) {
        return Patron.builder()
                .patronName(patronDto.patronName())
                .email(patronDto.email())
                .phoneNumber(patronDto.phoneNumber())
                .registrationDate(patronDto.registrationDate())
                .build();
    }

    public PatronDto toDto(Patron entity) {
        return PatronDto.builder()
                .patronId(entity.getPatronId())
                .patronName(entity.getPatronName())
                .email(entity.getEmail())
                .phoneNumber(entity.getEmail())
                .registrationDate(entity.getRegistrationDate())
                .build();
    }
}
