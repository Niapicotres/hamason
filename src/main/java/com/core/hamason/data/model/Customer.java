package com.core.hamason.data.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "CUSTOMERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String username; // Se usa el mismo username que en User

    @OneToOne
    @MapsId
    @JoinColumn(name = "username")
    private User user; // Relación con User

    private String fullName;
    private String email;
    private String phone;
    private String address;

    @Column(nullable = false)
    private String cardNumber; // Número de tarjeta

    @Column(nullable = false)
    private LocalDate cardExpiryDate; // Fecha de caducidad de la tarjeta

}
