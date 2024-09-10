package mypaymentservice.mypaymentservice.exrate;

import mypaymentservice.mypaymentservice.api.ApiTemplate;
import mypaymentservice.mypaymentservice.paymentservice.ExRateProvider;

import java.math.BigDecimal;

public class WebApiExRateProvider implements ExRateProvider {
    private final ApiTemplate apiTemplate;

    public WebApiExRateProvider(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    //클라이언트 가  callBack 을 만들어서 호출한다. -> 템플릿 메서드를
    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return apiTemplate.getExRate(url);
    }
}
