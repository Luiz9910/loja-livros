package com.lojabiblioteca.controller;

import com.lojabiblioteca.dto.Book.BookDTO;
import com.lojabiblioteca.dto.Book.BookResponseDTO;
import com.lojabiblioteca.dto.Book.BookUpdateDTO;
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

    /*
    @GetMapping("{id}")
    public BookResponseDTO getBookByName(@PathVariable String name) {
        return this.bookService.getBookByName(name);
    }*/

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDTO create(@Valid @RequestBody BookDTO book) {
        return this.bookService.create(book);
    }

    @PutMapping("{id}")
    public BookResponseDTO update(@PathVariable Long id, @Valid @RequestBody BookUpdateDTO book) {
        return this.bookService.update(id, book);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.bookService.delete(id);
    }
}
