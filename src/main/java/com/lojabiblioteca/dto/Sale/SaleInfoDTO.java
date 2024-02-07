package com.lojabiblioteca.dto.Sale;

import com.lojabiblioteca.dto.Book.BookDTO;
import com.lojabiblioteca.dto.Book.BookInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleInfoDTO {
    private String user;
    private String date;
    private BigDecimal total;
    private List<BookInfoDTO> books;
}
