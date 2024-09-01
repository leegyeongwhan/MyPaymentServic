package mypaymentservic.mypaymentservic.paymentservice;

import mypaymentservic.mypaymentservic.exrate.WebApiExRateProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    @Test
    @DisplayName("prepare 메서드가 요구사항의 3가지 조건을 잘 충족햇는지 검증")
    void prepare() throws IOException {
        PaymentService paymentService = new PaymentService(new WebApiExRateProvider());
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);
        //환율 정보를 가져온다
        assertThat(payment).isNotNull();
        //원화 환삼금액 계산
        assertThat(payment.getConvertedAmount()).isEqualTo(payment.getExRate().multiply(payment.getForeignCurrencyAmount()));
        //우너화환삼금액의 유효시간 계산
        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());

    }
}