package mypaymentservic.mypaymentservic.paymentservice;

import java.math.BigDecimal;

public interface ExRateProvider {

    BigDecimal getExRate(String currency);
}
