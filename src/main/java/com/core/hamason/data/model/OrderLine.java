package com.core.hamason.data.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ORDER_LINES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;  // Relación con el pedido al que pertenece

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // Relación con el producto que se está comprando

    @Column(nullable = false)
    private Integer quantity;  // Número de unidades compradas

    @Column(nullable = false)
    private Double unitPrice;  // Precio del producto en el momento del pedido

    @Column(nullable = false)
    private Double discount;  // Descuento aplicado (0.0 si no tiene)

}
