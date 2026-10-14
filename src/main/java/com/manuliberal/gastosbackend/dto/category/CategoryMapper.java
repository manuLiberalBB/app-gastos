package com.manuliberal.gastosbackend.dto.category;

import com.manuliberal.gastosbackend.model.Category;

public class CategoryMapper {

    // 1. De Request a Entidad
    public static Category toEntity(CategoryRequest request) {
        if (request == null) {
            return null;
        }

        Category category = new Category();
        category.setName(request.name());
        category.setType(request.type());
        category.setColor(request.color());
        category.setEmoji(request.emoji());
        // El usuario no se setea acá; se asocia en el servicio mediante el userId
        return category;
    }

    // 2. De Entidad a Response
    public static CategoryResponse toResponse(Category category) {
        if (category == null) {
            return null;
        }

        Long userId = (category.getUser() != null) ? category.getUser().getId() : null;

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getType(),
                category.getColor(),
                category.getEmoji(),
                userId
        );
    }
}