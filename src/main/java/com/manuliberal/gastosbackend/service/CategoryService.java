package com.manuliberal.gastosbackend.service;

import com.manuliberal.gastosbackend.model.Category;
import com.manuliberal.gastosbackend.model.User;
import com.manuliberal.gastosbackend.repository.CategoryRepository;
import com.manuliberal.gastosbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    // Inyectamos tanto el repositorio de categorías como el de usuarios
    public CategoryService(CategoryRepository categoryRepository,UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    // Crear una categoría asignada a un usuario
    public Category createCategory(Long userId, Category category) {
        // Regla de negocio: Verificar que el usuario exista
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));

        // Asignamos el usuario a la categoría y guardamos
        category.setUser(user);
        return categoryRepository.save(category);
    }

    // Obtener la lista de categorías de un usuario
    public List<Category> getCategoriesByUserId(Long userId) {
        return categoryRepository.findByUserId(userId);
    }
}
