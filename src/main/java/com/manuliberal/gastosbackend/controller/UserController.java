package com.manuliberal.gastosbackend.controller;


import com.manuliberal.gastosbackend.dto.user.UserMapper;
import com.manuliberal.gastosbackend.dto.user.UserRequest;
import com.manuliberal.gastosbackend.dto.user.UserResponse;
import com.manuliberal.gastosbackend.model.User;
import com.manuliberal.gastosbackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
        // 1. Convertimos el DTO de entrada a Entidad
        User userToCreate = UserMapper.toEntity(request);

        // 2. Ejecutamos la lógica de negocio en el servicio
        User savedUser = userService.createUser(userToCreate);

        // 3. Convertimos la entidad guardada a DTO de salida y respondemos HTTP 201 Created
        UserResponse response = UserMapper.toResponse(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        // 1. Buscamos el usuario mediante el servicio
        User user = userService.getUserById(id);
        // 2. Mapeamos a UserResponse para ocultar campos sensibles como la password
        UserResponse response = UserMapper.toResponse(user);
        return ResponseEntity.ok(response);
    }
}
