package mypaymentservice.mypaymentservice.exrate;

import mypaymentservice.mypaymentservice.paymentservice.ExRateProvider;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SimpleExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) {
        if (currency.equals("USD")) return BigDecimal.valueOf(1000);
        throw new IllegalArgumentException("지원되지않는 통화입니다.");
    }
}
