package mypaymentservice.mypaymentservice.order;

import mypaymentservice.mypaymentservice.OrderConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
class OrderServiceSpringTest {
    @Autowired
    OrderService orderService;
    @Autowired
    DataSource dataSource;

    @Test
    void creteOrder() {
        var order = orderService.createOrder("100", BigDecimal.TEN);
        assertThat(order.getId()).isGreaterThan(0);
    }

    @Test
    void createOrders() {
        List<OrderReq> list = List.of(
                new OrderReq("0023", BigDecimal.ONE),
                new OrderReq("0011", BigDecimal.TWO)
        );
        var orders = orderService.createOrders(list);
        assertThat(orders).hasSize(2);
        orders.forEach(order -> assertThat(order.getId()).isGreaterThan(0));
    }

    @Test
    void createOrders2() {
        List<OrderReq> list = List.of(
                new OrderReq("0023", BigDecimal.ONE),
                new OrderReq("0023", BigDecimal.TWO)
        );
        Assertions.assertThatThrownBy(() -> orderService.createOrders(list))
                .isInstanceOf(DataIntegrityViolationException.class);
        JdbcClient client = JdbcClient.create(dataSource);
        var count = client.sql("select count(*) from orders where no = '0023'").query(Long.class).single();
        Assertions.assertThat(count).isEqualTo(0);
    }
}
