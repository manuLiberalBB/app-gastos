package com.manuliberal.gastosbackend.dto.category;

import com.manuliberal.gastosbackend.model.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        @NotBlank(message = "El nombre de la categoría no puede estar vacío")
        String name,

        @NotNull(message = "El tipo de categoría es obligatorio (EXPENSE o INCOME)")
        CategoryType type,

        String color,
        String emoji
) {
}