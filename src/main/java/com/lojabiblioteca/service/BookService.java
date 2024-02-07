package com.lojabiblioteca.service;

import com.lojabiblioteca.dto.Book.BookDTO;
import com.lojabiblioteca.dto.Book.BookResponseDTO;
import com.lojabiblioteca.dto.Book.BookUpdateDTO;
import com.lojabiblioteca.exception.BadRequestException;
import com.lojabiblioteca.exception.NotFoundException;
import com.lojabiblioteca.model.Author;
import com.lojabiblioteca.model.Book;
import com.lojabiblioteca.model.User.User;
import com.lojabiblioteca.repository.AuthorRepository;
import com.lojabiblioteca.repository.BookRepository;
import com.lojabiblioteca.repository.UserRepository;
import jakarta.transaction.Transactional;
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

    public List<BookResponseDTO> getBooks() {
        return bookRepository.findAll().stream().map(book -> {
            BookResponseDTO booksResponseDTO = mapper.map(book, BookResponseDTO.class);
            return booksResponseDTO;
        }).toList();
    }

    public BookResponseDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Livro não encontrado"));

        return mapper.map(book, BookResponseDTO.class);
    }

    public List<BookResponseDTO> getBooksByName(String name) {
        if (name.isEmpty()) {
            throw new BadRequestException("Nome do livro inválido");
        }

        List<Book> books = bookRepository.findByPartialName(name);
        if (books.isEmpty()) {
            throw new NotFoundException("Livros com esse nome não encontrado");
        }

        List<BookResponseDTO> booksResponse = books.stream().map(book -> {
            BookResponseDTO bookResponseDTO = mapper.map(book, BookResponseDTO.class);
            return bookResponseDTO;
        }).toList();

        return booksResponse;
    }

    @Transactional
    public BookResponseDTO create(BookDTO book) {
        User user = userRepository.findById(book.getUser_id())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado para publicar"));

        List<Author> authors = book.getAuthors().stream()
                .map(authorDto -> {
                    Author author = mapper.map(authorDto, Author.class);
                    return authorRepository.save(author);
                })
                .collect(Collectors.toList());

        Book newBook = mapper.map(book, Book.class);
        newBook.setPublisher(LocalDate.now());
        newBook.setUser(user);
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
        bookResponse.setQuantity(book.getQuantity());

        Book bookToMapper = bookRepository.save(bookResponse);
        return mapper.map(bookToMapper, BookResponseDTO.class);
    }

    public void delete(Long id) {
        bookRepository.findById(id)
                .orElseThrow(() ->  new NotFoundException("Book não encotrado para deletar"));

        bookRepository.deleteById(id);
    }
}
