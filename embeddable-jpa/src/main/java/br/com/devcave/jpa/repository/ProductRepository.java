package br.com.devcave.jpa.repository;

import br.com.devcave.jpa.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
