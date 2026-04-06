package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name="ORDER_ITEMS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @EmbeddedId
    private @Getter @Setter OrderItemKey key;

    private @Getter  @Setter String item;
    private @Getter  @Setter Double quantity;
    private @Getter  @Setter Double price;
    private @Getter  @Setter Double taxValue;


    @ManyToOne
    @JoinColumns({
        @JoinColumn( name= "user_id", referencedColumnName = "user_id", insertable = false, updatable = false),
        @JoinColumn( name= "order_id", referencedColumnName = "order_id", insertable = false, updatable = false)
    })
    private @Getter  @Setter Order order;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(key, orderItem.key);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key);
    }
}
