package mypaymentservice.mypaymentservice.paymentservice;

import mypaymentservice.mypaymentservice.OrderConfig;
import mypaymentservice.mypaymentservice.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
class OrderServiceSpringTest {
    @Autowired
    OrderService orderService;

    @Test
    void creteOrder() {
        var order = orderService.createOrder("100", BigDecimal.TEN);
        Assertions.assertThat(order.getId()).isGreaterThan(0);
    }
}
