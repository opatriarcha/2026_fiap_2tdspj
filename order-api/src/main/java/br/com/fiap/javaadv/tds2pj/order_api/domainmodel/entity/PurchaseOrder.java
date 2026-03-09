package br.com.fiap.javaadv.tds2pj.order_api.domainmodel.entity;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PurchaseOrder {
    private @Getter @Setter Long id;
    private @Getter @Setter String customerName;
    private @Getter @Setter OrderStatus status;
    private @Getter @Setter OffsetDateTime createdAt;
    private @Getter @Setter List<OrderItem> itens = new ArrayList<>();
}
