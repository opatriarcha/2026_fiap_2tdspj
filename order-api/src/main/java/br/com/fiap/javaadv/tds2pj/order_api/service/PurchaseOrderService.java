package br.com.fiap.javaadv.tds2pj.order_api.service;

import br.com.fiap.javaadv.tds2pj.order_api.domainmodel.entity.PurchaseOrder;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderService {
    public PurchaseOrder create(PurchaseOrder purchaseOrder) {
        System.out.println("FAZ DE CONTA QUE CRIOU SAPOHA");
        return purchaseOrder;
    }
}
