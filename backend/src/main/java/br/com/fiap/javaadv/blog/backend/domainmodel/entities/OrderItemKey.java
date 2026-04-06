package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItemKey {

    @Column(name = "ORDER_ID")
    private @Getter @Setter Long orderId;

    @Column(name = "ITEM_ID")
    private @Getter @Setter Long itemId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemKey that = (OrderItemKey) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(itemId, that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, itemId);
    }
}
