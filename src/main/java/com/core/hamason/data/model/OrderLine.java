package com.core.hamason.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "order_line")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;  // Relación con la tabla de pedidos

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;  // Relación con la tabla de productos

    @Column(nullable = false)
    private int quantity; // Unidades pedidas (mínimo 1)

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal pricePerUnit; // Precio del producto en el momento del pedido

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal discount; // Descuento aplicado en el momento de la compra (porcentaje 0.0 - 100.0)

    public BigDecimal getTotalPrice() {
        BigDecimal subtotal = pricePerUnit.multiply(BigDecimal.valueOf(quantity));
        BigDecimal discountAmount = subtotal.multiply(discount.divide(BigDecimal.valueOf(100)));
        return subtotal.subtract(discountAmount).setScale(2, RoundingMode.HALF_UP);
    }
}
