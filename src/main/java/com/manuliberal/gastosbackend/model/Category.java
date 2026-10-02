package com.manuliberal.gastosbackend.model;

import lombok.Data;
import jakarta.persistence.*;
@Data
@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryType type;

    private String color;
    private String emoji;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}