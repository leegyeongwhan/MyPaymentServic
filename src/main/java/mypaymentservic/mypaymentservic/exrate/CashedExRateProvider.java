package mypaymentservic.mypaymentservic.exrate;

import mypaymentservic.mypaymentservic.paymentservice.ExRateProvider;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CashedExRateProvider implements ExRateProvider {
    private final ExRateProvider target;
    private BigDecimal cashedExRate;
    private LocalDateTime cacheExpiryTime;

    public CashedExRateProvider(ExRateProvider target) {
        this.target = target;
    }

    //WebExRate를 넣어둔다 데코레이터 패턴
    @Override
    public BigDecimal getExRate(String currency) {
        if (cashedExRate == null || cacheExpiryTime.isBefore(LocalDateTime.now())) {
            cashedExRate = this.target.getExRate(currency);
            cacheExpiryTime = LocalDateTime.now().plusSeconds(3);
            System.out.println("cash update");
        }
        return cashedExRate;
    }
}
