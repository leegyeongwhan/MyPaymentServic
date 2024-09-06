package mypaymentservic.mypaymentservic.paymentservice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {
    Clock clock;

    @BeforeEach
    void beforeEach() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    @DisplayName("prepare 메서드가 요구사항의 3가지 조건을 잘 충족햇는지 검증")
    void convertedAmount() throws IOException {
        testAmount(valueOf(500), valueOf(5_000), this.clock);
        testAmount(valueOf(1000), valueOf(10_000), this.clock);
        testAmount(valueOf(3000), valueOf(30_000), this.clock);

        //환율 정보를 가져온다
        //원화 환삼금액 계산
        //우너화환삼금액의 유효시간 계산
    }

    @Test
    void validUntil() throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1_000)), clock);
        Payment payment = paymentService.prepare(1L, "USD", TEN);
        LocalDateTime localDateTime = LocalDateTime.now(this.clock).plusMinutes(30);
        Assertions.assertThat(payment.getValidUntil()).isEqualTo(localDateTime);
    }

    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);
        Payment payment = paymentService.prepare(1L, "USD", TEN);

        assertThat(payment.getExRate()).isEqualTo(exRate);
        assertThat(payment.getConvertedAmount()).isEqualTo(convertedAmount);
    }
}