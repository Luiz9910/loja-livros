package com.lojabiblioteca.dto.Book;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSaleDTO {
    @NotBlank(message = "O item da venda obrigatório")
    private long bookById;

    @NotBlank(message = "Campo quantidade é obrigatório")
    private int quantity;
}
