package br.com.fiap.javaadv.tds2pj.order_api.service;

import br.com.fiap.javaadv.tds2pj.order_api.domainmodel.entity.OrderStatus;
import br.com.fiap.javaadv.tds2pj.order_api.domainmodel.entity.PurchaseOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class PurchaseOrderService {
    public PurchaseOrder create(PurchaseOrder purchaseOrder) {
        System.out.println("FAZ DE CONTA QUE CRIOU SAPOHA");
        purchaseOrder.setId(1L);
        return purchaseOrder;
    }

    public PurchaseOrder update(PurchaseOrder purchaseOrder) {
        System.out.println("FAZ DE CONTA QUE CRIOU SAPOHA");
        return purchaseOrder;
    }

    public void remove(Long id) {
        System.out.println("FAZ DE CONTA QUE DELETOU SAPOHA");
    }

    public PurchaseOrder findById(Long id) {

        return new PurchaseOrder(id, null, null, null, null);
    }

    public Page findAllPaged(Pageable pageable) {
        return null;
    }

    public Page<PurchaseOrder> search(String customerName, OrderStatus status, BigDecimal minTotal, Pageable pageable) {
        return null;
    }
}
