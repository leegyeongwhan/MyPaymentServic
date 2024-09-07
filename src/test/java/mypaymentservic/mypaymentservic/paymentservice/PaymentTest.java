package mypaymentservic.mypaymentservic.paymentservice;

import mypaymentservic.mypaymentservic.exrate.WebApiExRateProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;

class PaymentTest {

    Clock clock;
    ExRateProvider exRateProvider;
    Payment payment;

    @BeforeEach
    void init() throws IOException {
        clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        exRateProvider = new WebApiExRateProvider();
        payment = Payment.createPrepare(
                1L, "USD", BigDecimal.TEN, exRateProvider, clock
        );
    }

    @Test
    void createPrepared() throws IOException {
        Assertions.assertThat(payment.getCurrency()).isEqualTo("USD");
        Assertions.assertThat(payment.getExRate()).isEqualTo(exRateProvider.getExRate("USD"));
        Assertions.assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.TEN.multiply(payment.exRate));
        Assertions.assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30));
    }

    @Test
    void isValid() throws IOException {
        Payment payment = Payment.createPrepare(
                1L, "USD", BigDecimal.TEN, exRateProvider, clock
        );
        Assertions.assertThat(payment.isValid(clock)).isTrue();
        Assertions.assertThat(payment.isValid(Clock.offset(clock, Duration.of(30, ChronoUnit.MINUTES)))).isFalse();
    }
}
