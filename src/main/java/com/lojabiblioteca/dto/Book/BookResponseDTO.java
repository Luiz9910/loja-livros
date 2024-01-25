package com.lojabiblioteca.dto.Book;

import com.lojabiblioteca.model.Author;
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
    private Author author;
    private String language;
    private String publisher;
    private int pages;
}
