package com.core.hamason.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private int rating; // 1 a 5

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal discount; // porcentaje entre 0.0 y 100.0

    @ManyToOne
    @JoinColumn(name = "familia_id", nullable = false)
    private FamilyCategory familyCategory; // Relación con la tabla de familias
}
