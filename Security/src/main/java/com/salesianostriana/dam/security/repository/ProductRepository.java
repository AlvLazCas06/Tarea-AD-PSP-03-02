package com.salesianostriana.dam.security.repository;

import com.salesianostriana.dam.security.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
