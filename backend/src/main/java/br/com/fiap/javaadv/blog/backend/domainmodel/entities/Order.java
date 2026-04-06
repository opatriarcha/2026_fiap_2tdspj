package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="ORDERS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @EmbeddedId
    private @Getter @Setter OrderKey key;
    private @Getter @Setter Double totalItemQuantity;
    private @Getter @Setter Double totalProductsValue;
    private @Getter @Setter Double totalTaxValue;

    @OneToMany( mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @ManyToOne
    @JoinColumn( name= "user_id", insertable = false, updatable = false)
    private @Getter @Setter User user;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(key, order.key);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key);
    }
}
