package mypaymentservic.mypaymentservic;

import mypaymentservic.mypaymentservic.exrate.CashedExRateProvider;
import mypaymentservic.mypaymentservic.paymentservice.ExRateProvider;
import mypaymentservic.mypaymentservic.exrate.WebApiExRateProvider;
import mypaymentservic.mypaymentservic.paymentservice.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class PaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public ExRateProvider cashedProvider() {
        return new CashedExRateProvider(exRateProvider());
    }

}