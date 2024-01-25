package com.lojabiblioteca.dto.Book;

import com.lojabiblioteca.model.Author;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO {
    @NotBlank(message = "Campo nome é obrigatório")
    private String name;

    @NotNull(message = "Campo author é obrigatório")
    private Author author;

    @NotBlank(message = "Campo linguagem é obrigatório")
    private String language;

    @NotNull(message = "Campo páginas é obrigatório")
    @Min(1)
    private Integer pages;

    @NotNull(message = "Campo id_user obrigatório")
    private long user_id;
}
