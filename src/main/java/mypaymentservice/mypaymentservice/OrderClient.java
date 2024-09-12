package mypaymentservice.mypaymentservice;

import mypaymentservice.mypaymentservice.order.Order;
import mypaymentservice.mypaymentservice.order.OrderService;
import mypaymentservice.mypaymentservice.order.OrderServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class OrderClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
        OrderService orderService = beanFactory.getBean(OrderServiceImpl.class);
        Order order = orderService.createOrder("100", BigDecimal.TEN);
        System.out.println(order);
    }
}
