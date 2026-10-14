package com.manuliberal.gastosbackend.dto.category;

import com.manuliberal.gastosbackend.model.CategoryType;

public record CategoryResponse(
        Long id,
        String name,
        CategoryType type,
        String color,
        String emoji,
        Long userId
) {
}