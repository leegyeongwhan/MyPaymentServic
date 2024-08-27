package mypaymentservic.mypaymentservic;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {

    public static void main(String[] args) throws IOException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);
        OrderService orderService = beanFactory.getBean(OrderService.class);

        //둘이 같음 - true > spring 싱글 레지스터리 역할을 하고있따.
        System.out.println(paymentService.getProvider() == orderService.exRateProvider);

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
