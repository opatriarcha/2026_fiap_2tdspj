package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Order;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.QOrder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    public List<Order> findQuantityGreaterThanCriteria(int quantity){
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(
                builder.greaterThan(root.get("totalQuantity"), quantity)
        );
        query
                .select(root)
                .where(predicates);
        return this.entityManager.createQuery(query).getResultList();

    }

    public List<Order> findByQuantityGreaterThanQueryDsl( int minQuantity){
        QOrder qorder = QOrder.order;
        BooleanExpression predicate = qorder.totalQuantity.gt(minQuantity);
        var factory = new JPAQueryFactory(this.entityManager);

        return factory
                .selectFrom(qorder)
                .where(predicate)
                .fetch();
    }

    public List<Order> findByQuantityGreaterThanQueryDslSimplified( int minQuantity){
        QOrder o = QOrder.order;
        var factory = new JPAQueryFactory(this.entityManager);

        return factory
                .selectFrom(o)
                .where(
                        o.totalQuantity.gt(minQuantity)
                )
                .fetch();
    }


}
