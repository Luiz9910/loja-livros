package com.lojabiblioteca.dto.Book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO {
    @NotBlank(message = "Campo nome é obrigatório")
    private String name;

    @NotBlank(message = "Campo linguagem é obrigatório")
    private String language;

    @NotBlank(message = "Campo ano é obrigatório")
    private String year;

    @NotBlank(message = "Campo publicado é obrigatório")
    private String publisher;

    @NotNull(message = "Campo páginas é obrigatório")
    @Min(1)
    private Integer pages;
}
