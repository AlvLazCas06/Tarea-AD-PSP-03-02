package com.salesianostriana.dam.security.repository;

import com.salesianostriana.dam.security.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
