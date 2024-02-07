package com.lojabiblioteca.service;

import com.lojabiblioteca.dto.Book.BookSaleDTO;
import com.lojabiblioteca.dto.Sale.SaleDTO;
import com.lojabiblioteca.exception.BadRequestException;
import com.lojabiblioteca.exception.NotFoundException;
import com.lojabiblioteca.model.Book;
import com.lojabiblioteca.model.ItemSale;
import com.lojabiblioteca.model.Sale;
import com.lojabiblioteca.model.User.User;
import com.lojabiblioteca.repository.BookRepository;
import com.lojabiblioteca.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public String save(SaleDTO sale) throws Exception {
        User user = userRepository
                .findById(sale.getUserid())
                .orElseThrow(() -> new NotFoundException("Usuário não encotrado para realizar a venda"));

        Sale newSale = new Sale();
        newSale.setDate(LocalDate.now());
        newSale.setUser(user);

        List<ItemSale> items = getItemSale(sale.getItems());
        return "tudo certo";
    }

    private List<ItemSale> getItemSale(List<BookSaleDTO> items) throws Exception {
        return items.stream().map(item -> {
            Book book = bookRepository.findById(item.getProductid())
                    .orElseThrow(() -> new NotFoundException("Livro para a venda não encotrado"));

            ItemSale itemSale = new ItemSale();
            itemSale.setBook(book);
            itemSale.setQuantity(item.getQuantity());

            return itemSale;
        }).toList();
    }
}
