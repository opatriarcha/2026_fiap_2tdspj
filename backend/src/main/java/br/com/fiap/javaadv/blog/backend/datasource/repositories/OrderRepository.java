package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Order;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.OrderKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, OrderKey>,
        QuerydslPredicateExecutor<Order>,
        OrderRepositoryCustom{

    List<Order> findByTotalPrice( Double totalPrice);


    //JPQL
    @Query("SELECT o from Order o WHERE o.totalPrice >= :price" )
    List<Order> FindOrdersWithbgjwbgjwrbgjebrgPriceGreaterThenOrEqual(double price);

}
