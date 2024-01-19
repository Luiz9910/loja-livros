package com.lojabiblioteca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Campo nome é obrigatório")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Campo lingua é obrigatório")
    private String language;

    @Column(nullable = false)
    @NotBlank(message = "Campo ano é obrigatório")
    private String year;

    @Column(nullable = false)
    @NotBlank(message = "Campo publicado é obrigatório")
    private String publisher;

    @Column(nullable = false)
    @Min(1)
    @NotNull(message = "Campo páginas é obrigatório")
    private int pages;
}
