package com.lojabiblioteca.dto.Book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookUpdateDTO {
    @NotBlank(message = "Campo nome é obrigatório")
    private String name;

    @NotBlank(message = "Campo linguagem é obrigatório")
    private String language;

    @NotNull(message = "Campo quantidade obrigatório")
    private int quantity;

    @NotNull(message = "Campo de preço obrigatório")
    private BigDecimal price;

    @NotNull(message = "Campo páginas é obrigatório")
    @Min(1)
    private Integer pages;
}
