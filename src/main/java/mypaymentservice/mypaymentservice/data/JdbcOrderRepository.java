package mypaymentservice.mypaymentservice.data;

import jakarta.annotation.PostConstruct;
import mypaymentservice.mypaymentservice.order.Order;
import mypaymentservice.mypaymentservice.order.OrderRepository;
import org.springframework.jdbc.core.simple.JdbcClient;

import javax.sql.DataSource;

public class JdbcOrderRepository implements OrderRepository {

    private final JdbcClient jdbcClient;

    public JdbcOrderRepository(DataSource dataSource) {
        this.jdbcClient = JdbcClient.create(dataSource);
    }

    @PostConstruct
    void initDb(){
    }

    @Override
    public void save(Order order) {

    }
}
