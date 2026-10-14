package com.manuliberal.gastosbackend.controller;

import com.manuliberal.gastosbackend.dto.category.CategoryMapper;
import com.manuliberal.gastosbackend.dto.category.CategoryRequest;
import com.manuliberal.gastosbackend.dto.category.CategoryResponse;
import com.manuliberal.gastosbackend.model.Category;
import com.manuliberal.gastosbackend.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CategoryResponse> createCategory(
            @RequestParam Long userId,
            @Valid @RequestBody CategoryRequest request) {

        Category categoryToCreate = CategoryMapper.toEntity(request);
        Category savedCategory = categoryService.createCategory(userId, categoryToCreate);
        CategoryResponse response = CategoryMapper.toResponse(savedCategory);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CategoryResponse>> getCategoriesByUserId(@PathVariable Long userId) {
        List<Category> categories = categoryService.getCategoriesByUserId(userId);

        // Transformamos List<Category> a List<CategoryResponse>
        List<CategoryResponse> response = categories.stream()
                .map(CategoryMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }
}