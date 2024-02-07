package com.lojabiblioteca.controller;

import com.lojabiblioteca.dto.Sale.SaleDTO;
import com.lojabiblioteca.service.SaleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale")
public class SaleController {
    private SaleService saleService;

    @PostMapping
    public String create(@RequestBody SaleDTO saleDTO) throws Exception {
        return saleService.save(saleDTO);
    }
}
