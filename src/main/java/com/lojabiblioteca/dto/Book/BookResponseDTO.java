package com.lojabiblioteca.dto.Book;

import com.lojabiblioteca.model.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookResponseDTO {
    private String id;
    private String name;
    private List<Author> authors;
    private String language;
    private String publisher;
    private int quantity;
    private int pages;
}
