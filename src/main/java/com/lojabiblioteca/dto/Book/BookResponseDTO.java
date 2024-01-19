package com.lojabiblioteca.dto.Book;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookResponseDTO {
    private String id;
    private String name;
    private String language;
    private String year;
    private String publisher;
    private int pages;
}
