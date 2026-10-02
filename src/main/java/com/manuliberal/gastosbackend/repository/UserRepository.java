package com.manuliberal.gastosbackend.repository;

import com.manuliberal.gastosbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Metodo personalizado: Spring entiende el nombre del metodo
    // y genera un "SELECT * FROM users WHERE email = ?"
    Optional<User> findByEmail(String email);
}