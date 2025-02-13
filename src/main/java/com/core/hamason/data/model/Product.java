package com.core.hamason.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "family_category_id", nullable = false)
    private FamilyCategory familyCategory; // Relación con la tabla de familias
    
    public BigDecimal getPrecioFinal() {
        if (discount == null || discount.compareTo(BigDecimal.ZERO) == 0) {
            return price.setScale(2, RoundingMode.HALF_UP); // Redondea a 2 decimales
        }
        return price.multiply(BigDecimal.ONE.subtract(discount.divide(BigDecimal.valueOf(100))))
                   .setScale(2, RoundingMode.HALF_UP); // Redondea a 2 decimales
    }
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderLine> orderLines = new ArrayList<>();


}
