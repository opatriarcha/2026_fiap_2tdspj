package br.com.fiap.javaadv.tds2pj.order_api.resources;

import br.com.fiap.javaadv.tds2pj.order_api.domainmodel.entity.PurchaseOrder;
import br.com.fiap.javaadv.tds2pj.order_api.service.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class PurchaseOrderController {
    private final PurchaseOrderService orderService;

    @PostMapping // POST http://localhost:8080/api/orders
    public ResponseEntity<PurchaseOrder> create(@RequestBody PurchaseOrder purchaseOrder ){
        PurchaseOrder response = this.orderService.create(purchaseOrder);

        return ResponseEntity.ok(response);
    }

}
