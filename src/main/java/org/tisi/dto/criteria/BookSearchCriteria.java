package org.tisi.dto.criteria;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Schema(
        name = "BookSearchCriteria",
        description = "Search criteria for filtering and searching books with multiple optional parameters"
)
public record BookSearchCriteria(
        @Parameter(
                description = "Filter books by title (partial match, case-insensitive)",
                example = "Harry Potter",
                required = false
        )
        @Schema(
                description = "Book title to search for (partial match)",
                example = "Harry Potter",
                nullable = true
        )
        String title,

        @Parameter(
                description = "Filter books by author name (partial match, case-insensitive)",
                example = "Rowling",
                required = false
        )
        @Schema(
                description = "Author name to search for (partial match)",
                example = "Rowling",
                nullable = true
        )
        String authorName,

        @Parameter(
                description = "Filter books by exact ISBN",
                example = "9780747532699",
                required = false
        )
        @Schema(
                description = "Exact ISBN of the book",
                example = "9780747532699",
                pattern = "^(97(8|9))?\\d{9}(\\d|X)$",
                nullable = true
        )
        String isbn,

        @Parameter(
                description = "Filter books published from this year onwards",
                example = "1990",
                required = false
        )
        @Schema(
                description = "Minimum publication year",
                example = "1990",
                minimum = "1000",
                maximum = "2026",
                nullable = true
        )
        Integer yearFrom,

        @Parameter(
                description = "Filter books published up to this year",
                example = "2024",
                required = false
        )
        @Schema(
                description = "Maximum publication year",
                example = "2024",
                minimum = "1000",
                maximum = "2026",
                nullable = true
        )
        Integer yearTo,

        @Parameter(
                description = "Filter books by availability status",
                example = "true",
                required = false
        )
        @Schema(
                description = "Whether the book is currently available for borrowing",
                example = "true",
                nullable = true
        )
        Boolean available,

        @Parameter(
                description = "Filter books borrowed by a specific patron ID",
                example = "1",
                required = false
        )
        @Schema(
                description = "ID of the patron who borrowed the book",
                example = "1",
                minimum = "1",
                nullable = true
        )
        Long patronId,

        @Parameter(
                description = "Filter books borrowed from this date (YYYY-MM-DD)",
                example = "2024-01-01",
                required = false
        )
        @Schema(
                description = "Start date of borrow period",
                example = "2024-01-01",
                format = "date",
                type = "string",
                nullable = true
        )
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate borrowedFrom,

        @Parameter(
                description = "Filter books borrowed up to this date (YYYY-MM-DD)",
                example = "2024-12-31",
                required = false
        )
        @Schema(
                description = "End date of borrow period",
                example = "2024-12-31",
                format = "date",
                type = "string",
                nullable = true
        )
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate borrowedTo
) {
    @Schema(
            description = "Whether all search criteria are empty",
            hidden = true
    )
    public boolean isEmpty() {
        return title == null && authorName == null && isbn == null
                && yearFrom == null && yearTo == null && available == null
                && patronId == null && borrowedFrom == null && borrowedTo == null;
    }
}