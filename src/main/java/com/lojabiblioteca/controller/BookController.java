package com.lojabiblioteca.controller;

import com.lojabiblioteca.dto.Book.BookDTO;
import com.lojabiblioteca.dto.Book.BookResponseDTO;
import com.lojabiblioteca.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDTO create(@Valid @RequestBody BookDTO book) {
        return this.bookService.create(book);
    }

    @PutMapping("{id}")
    public BookResponseDTO update(@PathVariable Long id, @Valid @RequestBody BookDTO book) {
        return this.bookService.update(id, book);
    }
}
