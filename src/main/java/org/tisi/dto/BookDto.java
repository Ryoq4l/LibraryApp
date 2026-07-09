package org.tisi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long bookId;
    private String title;
    private String authorName;
    private Long authorId;
    private String isbn;
    private Integer publicationYear;
    private Integer quantity;
    private Integer availableQuantity;
}