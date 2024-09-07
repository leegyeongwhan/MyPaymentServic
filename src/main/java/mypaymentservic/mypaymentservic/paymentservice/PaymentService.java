package mypaymentservic.mypaymentservic.paymentservice;

import java.math.BigDecimal;
import java.time.Clock;

public class PaymentService {

    private final ExRateProvider provider;
    private final Clock clock;

    public PaymentService(ExRateProvider provider, Clock clock) {
        this.provider = provider;
        this.clock = clock;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) {
        return Payment.createPrepare(orderId, currency, foreignCurrencyAmount, provider, clock);
    }
}
