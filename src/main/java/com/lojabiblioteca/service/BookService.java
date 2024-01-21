package com.lojabiblioteca.service;

import com.lojabiblioteca.dto.Book.BookDTO;
import com.lojabiblioteca.dto.Book.BookResponseDTO;
import com.lojabiblioteca.dto.User.UserResponseDTO;
import com.lojabiblioteca.exception.NotFoundException;
import com.lojabiblioteca.model.Book;
import com.lojabiblioteca.model.User.User;
import com.lojabiblioteca.repository.BookRepository;
import com.lojabiblioteca.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    private ModelMapper mapper = new ModelMapper();

    public BookResponseDTO create(BookDTO book) {
        User user = userRepository.findById(book.getUser_id())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        Book newBook = mapper.map(book, Book.class);
        newBook.setPublisher(LocalDate.now());
        newBook.setUser(user);

        Book bookResponse = bookRepository.save(newBook);
        BookResponseDTO bookResponseDTO = mapper.map(bookResponse, BookResponseDTO.class);
        return bookResponseDTO;
    }
}
