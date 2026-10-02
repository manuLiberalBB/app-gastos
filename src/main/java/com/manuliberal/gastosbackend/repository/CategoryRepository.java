package com.manuliberal.gastosbackend.repository;

import com.manuliberal.gastosbackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Para consultar las categorías asociadas a un usuario en particular
    List<Category> findByUserId(Long userId);
}
