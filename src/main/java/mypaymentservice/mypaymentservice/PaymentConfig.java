package mypaymentservice.mypaymentservice;

import mypaymentservice.mypaymentservice.api.ApiTemplate;
import mypaymentservice.mypaymentservice.api.ExApiExRateExtractor;
import mypaymentservice.mypaymentservice.api.SimpleApiExecutor;
import mypaymentservice.mypaymentservice.exrate.CashedExRateProvider;
import mypaymentservice.mypaymentservice.exrate.WebApiExRateProvider;
import mypaymentservice.mypaymentservice.paymentservice.ExRateProvider;
import mypaymentservice.mypaymentservice.paymentservice.PaymentService;
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
        return new WebApiExRateProvider(apiTemplate());
    }

    @Bean
    public ApiTemplate apiTemplate() {
        return new ApiTemplate(new SimpleApiExecutor(), new ExApiExRateExtractor());
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