package mypaymentservice.mypaymentservice;

import mypaymentservice.mypaymentservice.data.JdbcOrderRepository;
import mypaymentservice.mypaymentservice.order.OrderRepository;
import mypaymentservice.mypaymentservice.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {
    @Bean
    public OrderService orderService(
            PlatformTransactionManager transactionManager, OrderRepository orderRepository) {
        return new OrderService(orderRepository, transactionManager);
    }

    @Bean
    public OrderRepository orderRepository(DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }
}
