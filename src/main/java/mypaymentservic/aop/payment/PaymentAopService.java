package mypaymentservic.aop.payment;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import mypaymentservic.mypaymentservic.paymentservice.ExRateProvider;
import mypaymentservic.mypaymentservic.paymentservice.Payment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Getter
@Slf4j
public class PaymentAopService {

    private final ExRateProvider provider;
    private final PaymentRepository paymentRepository;

    public PaymentAopService(ExRateProvider provider, PaymentRepository paymentRepository) {
        this.provider = provider;
        this.paymentRepository = paymentRepository;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        //여기서 오는 Provider 는 object factory에서 결정
        BigDecimal exRate = provider.getExRate(currency);
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);
        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    public void orderItem(String itemId) {
        log.info("[orderService] 실행");
        paymentRepository.save(itemId);
    }
}
