package com.lojabiblioteca.service;

import com.lojabiblioteca.dto.Book.BookDTO;
import com.lojabiblioteca.dto.Book.BookResponseDTO;
import com.lojabiblioteca.dto.Book.BookUpdateDTO;
import com.lojabiblioteca.dto.User.UserResponseDTO;
import com.lojabiblioteca.exception.NotFoundException;
import com.lojabiblioteca.model.Author;
import com.lojabiblioteca.model.Book;
import com.lojabiblioteca.model.User.User;
import com.lojabiblioteca.repository.AuthorRepository;
import com.lojabiblioteca.repository.BookRepository;
import com.lojabiblioteca.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private ModelMapper mapper = new ModelMapper();

    /*
    public BookResponseDTO getBookByName(String name) {
        r
    }*/

    @Transactional
    public BookResponseDTO create(BookDTO bookDTO) {
        User user = userRepository.findById(bookDTO.getUser_id())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado para publicar"));

        Book newBook = mapper.map(bookDTO, Book.class);
        newBook.setPublisher(LocalDate.now());
        newBook.setUser(user);

        List<Author> authors = bookDTO.getAuthors().stream()
                .map(authorDTO -> {
                    Author author = new Author();
                    author.setName(authorDTO.getName());
                    author.setSurname(authorDTO.getSurname());
                    author.setBook(newBook);
                    return authorRepository.save(author);
                })
                .collect(Collectors.toList());


        newBook.setAuthors(authors);

        Book bookResponse = bookRepository.save(newBook);
        return mapper.map(bookResponse, BookResponseDTO.class);
    }



    public BookResponseDTO update(Long id, BookUpdateDTO book) {
        Book bookResponse = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book não encontrado para atualizar"));

        bookResponse.setName(book.getName());
        bookResponse.setLanguage(book.getLanguage());
        bookResponse.setPages(book.getPages());
        bookResponse.setPublisher(LocalDate.now());

        Book bookToMapper = bookRepository.save(bookResponse);
        return mapper.map(bookToMapper, BookResponseDTO.class);
    }

    public void delete(Long id) {
        bookRepository.findById(id)
                .orElseThrow(() ->  new NotFoundException("Book não encotrado para deletar"));

        bookRepository.deleteById(id);
    }
}
