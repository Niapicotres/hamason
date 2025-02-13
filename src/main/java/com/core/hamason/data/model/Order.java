package com.core.hamason.data.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate creationDate;
    
    private String status; // PROGRESS o FINISHED
    
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderLines;
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order order = (Order) obj;
        return Objects.equals(id, order.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
