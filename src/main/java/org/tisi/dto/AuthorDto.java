package org.tisi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private Long authorId;
    private String authorName;
    private String bio;
}