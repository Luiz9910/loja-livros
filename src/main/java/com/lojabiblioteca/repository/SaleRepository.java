package com.lojabiblioteca.repository;

import com.lojabiblioteca.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
