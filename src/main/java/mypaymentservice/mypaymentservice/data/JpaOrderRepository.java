package mypaymentservice.mypaymentservice.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mypaymentservice.mypaymentservice.order.Order;
import mypaymentservice.mypaymentservice.order.OrderRepository;

public class JpaOrderRepository implements OrderRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Order order) {
        em.persist(order);
    }
}
