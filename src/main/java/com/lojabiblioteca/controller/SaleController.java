package com.lojabiblioteca.controller;

import com.lojabiblioteca.dto.Sale.SaleDTO;
import com.lojabiblioteca.service.SaleService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @SneakyThrows
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody SaleDTO saleDTO) {
        return saleService.save(saleDTO);
    }
}
