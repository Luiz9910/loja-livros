package com.lojabiblioteca.dto.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookInfoDTO {
    private long id;
    private String title;
    private BigDecimal price;
    private int quantity;
}
