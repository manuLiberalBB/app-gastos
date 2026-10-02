package com.manuliberal.gastosbackend.controller;

import com.manuliberal.gastosbackend.model.Category;
import com.manuliberal.gastosbackend.model.User;
import com.manuliberal.gastosbackend.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping
    public Category createCategory(@RequestParam Long userId, @RequestBody Category category) {
        return categoryService.createCategory(userId,category);
    }

    // Obtener todas las categorias de una usuario
    @GetMapping("/user/{userId}")
    public List<Category> getCategoriesByUserId(@PathVariable Long userId) {
        return  categoryService.getCategoriesByUserId(userId);
    }


}
