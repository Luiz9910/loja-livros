package com.lojabiblioteca.service;

import com.lojabiblioteca.dto.Book.BookDTO;
import com.lojabiblioteca.dto.Book.BookResponseDTO;
import com.lojabiblioteca.dto.User.UserResponseDTO;
import com.lojabiblioteca.model.Book;
import com.lojabiblioteca.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    private ModelMapper mapper = new ModelMapper();

    public BookResponseDTO create(BookDTO book) {
        Book newBook = mapper.map(book, Book.class);
        Book bookResponse = bookRepository.save(newBook);
        BookResponseDTO bookResponseDTO = mapper.map(bookResponse, BookResponseDTO.class);
        return bookResponseDTO;
    }
}
