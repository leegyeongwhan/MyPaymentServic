package mypaymentservice.mypaymentservice.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mypaymentservice.mypaymentservice.order.Order;

public class OrderRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }
}
