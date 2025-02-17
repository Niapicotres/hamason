package com.core.hamason.data.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@DiscriminatorValue("CUSTOMER")  // Marca los registros de Customer en la tabla USERS
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Customer extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String shippingAddress;
    private String phone;
    private String creditCardNumber;
    private String creditCardExpiry;
}
