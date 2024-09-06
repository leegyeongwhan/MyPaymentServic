package mypaymentservic.mypaymentservic.paymentservice;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

public class PaymentService {

    private final ExRateProvider provider;
    private final Clock clock;

    public PaymentService(ExRateProvider provider, Clock clock) {
        this.provider = provider;
        this.clock = clock;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        //여기서 오는 Provider 는 object factory에서 결정
        BigDecimal exRate = provider.getExRate(currency);
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now(clock).plusMinutes(30);
        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    public ExRateProvider getProvider() {
        return provider;
    }
}
