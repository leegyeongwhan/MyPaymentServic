package mypaymentservic.mypaymentservic;

import mypaymentservic.mypaymentservic.exrate.CashedExRateProvider;
import mypaymentservic.mypaymentservic.paymentservice.ExRateProvider;
import mypaymentservic.mypaymentservic.exrate.WebApiExRateProvider;
import mypaymentservic.mypaymentservic.paymentservice.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cashedProvider());
    }

    @Bean
    public ExRateProvider cashedProvider() {
        return new CashedExRateProvider(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }

    @Bean
    public OrderService orderService() {
        return new OrderService(exRateProvider());
    }
}

class OrderService {
    ExRateProvider exRateProvider;

    public OrderService(ExRateProvider exRateProvider) {
        this.exRateProvider = exRateProvider;
    }
}
