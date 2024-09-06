package mypaymentservic.mypaymentservic;

import mypaymentservic.mypaymentservic.paymentservice.Payment;
import mypaymentservic.mypaymentservic.paymentservice.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment1 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment1);

        TimeUnit.SECONDS.sleep(1);

        System.out.println("--------------------\n");
        Payment payment2 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment2);

        TimeUnit.SECONDS.sleep(2);

        System.out.println("--------------------\n");
        Payment payment3 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment3);
    }
}
