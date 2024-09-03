package aop;

import lombok.extern.slf4j.Slf4j;
import mypaymentservic.aop.AspectV1;
import mypaymentservic.aop.payment.PaymentAopService;
import mypaymentservic.aop.payment.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(AspectV1.class)
class AopTest {

    @Autowired
    PaymentAopService paymentAopService;

    @Autowired
    PaymentRepository paymentRepository;

    @Test
    void aopInfo() {
        log.info("paymentService ={}", AopUtils.isAopProxy(paymentAopService));
        log.info("paymentRepository={}", AopUtils.isAopProxy(paymentRepository));

    }
}
