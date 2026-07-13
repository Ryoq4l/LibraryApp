package org.tisi.dto.criteria;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record BookSearchCriteria (
        String title,
        String authorName,
        String isbn,
        Integer yearFrom,
        Integer yearTo,
        Boolean available,
        Long patronId,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate borrowedFrom,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate borroweTo
){
    public boolean isEmpty() {
        return title == null && authorName == null && isbn == null
                && yearFrom == null && yearTo == null && available == null
                && patronId == null;
    }
}

