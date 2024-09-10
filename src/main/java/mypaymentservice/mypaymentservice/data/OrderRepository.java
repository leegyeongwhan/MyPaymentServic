package mypaymentservice.mypaymentservice.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import mypaymentservice.mypaymentservice.order.Order;

public class OrderRepository {
    private final EntityManagerFactory emf;

    public OrderRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Order order) {
        //em
        EntityManager em = emf.createEntityManager();
        //transaction
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        try {
            em.persist(order);
            em.flush();
            transaction.commit();

        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        } finally {
            if (em.isOpen()) em.close();
        }
    }
}
