package com.ddl.repository;

import com.ddl.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String > {
    Optional<Object> findByNameIgnoreCase(String type);
}
