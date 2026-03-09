package br.com.fiap.javaadv.tds2pj.order_api.domainmodel.entity;


import lombok.*;

import java.math.BigDecimal;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private @Getter @Setter Long id;
    private @Getter @Setter String productName;
    private @Getter @Setter Integer quantity;
    private @Getter @Setter BigDecimal unitPrice;
    private @Getter @Setter PurchaseOrder order;
}
