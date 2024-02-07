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
import com.lojabiblioteca.repository.ItemSaleRepository;
import com.lojabiblioteca.repository.SaleRepository;
import com.lojabiblioteca.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ItemSaleRepository itemSaleRepository;

    @Transactional
    public Long save(SaleDTO sale) throws Exception {
        User user = userRepository
                .findById(sale.getUserid())
                .orElseThrow(() -> new NotFoundException("Usuário não encotrando para realizar a compra"));

        Sale newSale = new Sale();
        newSale.setDate(LocalDate.now());
        newSale.setUser(user);

        List<ItemSale> itemSale = getBookList(sale.getItems());
        newSale = saleRepository.save(newSale);
        setItemSale(itemSale, newSale);

        return newSale.getId();
    }

    public void setItemSale(List<ItemSale> items, Sale newSale) {
        for (ItemSale item: items) {
            item.setSale(newSale);
            itemSaleRepository.save(item);
        }
    }

    private List<ItemSale> getBookList(List<BookSaleDTO> books) {
        return books.stream().map(item -> {
            Book book = bookRepository
                    .findById(item.getBookById())
                    .orElseThrow(() -> new NotFoundException("Book não encotrado para realizar a compra"));

            ItemSale itemSale = new ItemSale();
            itemSale.setBook(book);
            itemSale.setQuantity(item.getQuantity());

            if (item.getQuantity() == 0) {
                throw new BadRequestException("Valores considerados para compra é a partir de 1");
            } else if (book.getQuantity() == 0) {
                throw new BadRequestException("Produto sem estoque");
            } else if (book.getQuantity() < item.getQuantity()) {
                throw new BadRequestException("A quantidade de itens da venda (" + item.getQuantity() + ") é maior que a quantidade dísponivel em estoque (" + book.getQuantity() + ")");
            }

            int total = book.getQuantity() - item.getQuantity();
            book.setQuantity(total);
            bookRepository.save(book);

            return itemSale;
        }).toList();
    }
}
