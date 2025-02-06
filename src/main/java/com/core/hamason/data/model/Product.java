package com.core.hamason.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private int valoracion;

    @Column(nullable = false)
    private double descuento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryFamily familia;

    // Validaciones para asegurarnos de que los valores cumplen con los requisitos
    public void setPrecio(double precio) {
        if (precio < 0 || precio > 999.99) {
            throw new IllegalArgumentException("El precio debe estar entre 0 y 999.99 euros.");
        }
        this.precio = precio;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        this.stock = stock;
    }

    public void setValoracion(int valoracion) {
        if (valoracion < 1 || valoracion > 5) {
            throw new IllegalArgumentException("La valoración debe estar entre 1 y 5.");
        }
        this.valoracion = valoracion;
    }

    public void setDescuento(double descuento) {
        if (descuento < 0 || descuento > 100) {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 100%.");
        }
        this.descuento = descuento;
    }
}
