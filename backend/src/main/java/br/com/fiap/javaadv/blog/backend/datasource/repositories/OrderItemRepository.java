package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.OrderItem;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.OrderItemKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OrderItemRepository extends
        JpaRepository<OrderItem, OrderItemKey>,
        QuerydslPredicateExecutor<OrderItem>,
        OrderItemRepositoryCustom {
}
