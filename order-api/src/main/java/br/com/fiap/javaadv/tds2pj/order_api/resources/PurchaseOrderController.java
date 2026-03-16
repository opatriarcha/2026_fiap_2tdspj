package br.com.fiap.javaadv.tds2pj.order_api.resources;

import br.com.fiap.javaadv.tds2pj.order_api.domainmodel.entity.OrderStatus;
import br.com.fiap.javaadv.tds2pj.order_api.domainmodel.entity.PurchaseOrder;
import br.com.fiap.javaadv.tds2pj.order_api.service.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


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

    @PutMapping   //PUT http://localhost:8080/api/orders
    public ResponseEntity<PurchaseOrder> update(@RequestBody PurchaseOrder purchaseOrder){
        return ResponseEntity.ok(this.orderService.update(purchaseOrder));
    }

    @DeleteMapping ("/{id}")//DELETE http://localhost:8080/api/orders/{id}
    public ResponseEntity<Void> delete( @PathVariable Long id){
        this.orderService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}") // GET http://localhost:8080/api/orders/{id}
    public ResponseEntity<PurchaseOrder>  findById( @PathVariable Long id){
        return ResponseEntity.ok(this.orderService.findById(id));
    }

    @GetMapping  // GET http://localhost:8080/api/orders
    public ResponseEntity<Page<PurchaseOrder>> findAll(
            @PageableDefault(page=0, size=10, sort = "id")
            Pageable pageable){
        return ResponseEntity.ok(this.orderService.findAllPaged( pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PurchaseOrder>> search(
            @RequestParam( required = true) String customerName,
            @RequestParam( required = true ) OrderStatus status,
            @RequestParam( required = true ) BigDecimal minTotal,
            @PageableDefault( page = 0, size=10, sort = "id" ) Pageable pageable
            ){
        return ResponseEntity.ok(this.orderService.search(customerName, status, minTotal, pageable));
    }

}
