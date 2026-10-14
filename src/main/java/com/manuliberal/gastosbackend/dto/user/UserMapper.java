package com.manuliberal.gastosbackend.dto.user;

import com.manuliberal.gastosbackend.model.User;
// Si tenés createdAt en tu User, asegúrate de importar LocalDateTime
import java.time.LocalDateTime;

public class UserMapper {

    // 1. De Request a Entidad (Lo que entra va a la base de datos)
    public static User toEntity(UserRequest request) {
        if (request == null) {
            return null;
        }

        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());

        // El ID no se setea porque lo genera la BD.
        // El createdAt lo podemos setear acá o dejar que la BD/JPA lo haga.
        user.setCreatedAt(LocalDateTime.now());

        return user;
    }

    // 2. De Entidad a Response (Lo que sale de la base de datos va al cliente)
    public static UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}