package mypaymentservic.mypaymentservic;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CashedExRateProvider implements ExRateProvider {
    private final ExRateProvider target;
    private BigDecimal cashedExRate;
    private LocalDateTime cacheExpiryTime;

    public CashedExRateProvider(ExRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        if (cashedExRate == null || cacheExpiryTime.isBefore(LocalDateTime.now())) {
            cashedExRate = this.target.getExRate(currency);
            cacheExpiryTime = LocalDateTime.now().plusSeconds(3);
            System.out.println("cash update");
        }
        return cashedExRate;
    }
}
