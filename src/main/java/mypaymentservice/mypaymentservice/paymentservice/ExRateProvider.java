package mypaymentservice.mypaymentservice.paymentservice;

import java.math.BigDecimal;

public interface ExRateProvider {

    BigDecimal getExRate(String currency);
}
