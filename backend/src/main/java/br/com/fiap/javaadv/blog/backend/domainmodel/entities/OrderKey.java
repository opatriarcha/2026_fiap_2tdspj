package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderKey {

    @Column(name = "USER_ID")
    private @Getter @Setter UUID userId;

    @Column(name = "ORDER_ID")
    private @Getter @Setter Long orderId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderKey orderKey = (OrderKey) o;
        return Objects.equals(userId, orderKey.userId) && Objects.equals(orderId, orderKey.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, orderId);
    }
}
