package mypaymentservic.mypaymentservic.exrate;

import mypaymentservic.mypaymentservic.api.ApiTemplate;
import mypaymentservic.mypaymentservic.api.ExApiExRateExtractor;
import mypaymentservic.mypaymentservic.api.HttpClientApiExecutor;
import mypaymentservic.mypaymentservic.paymentservice.ExRateProvider;

import java.math.BigDecimal;

public class WebApiExRateProvider implements ExRateProvider {
    ApiTemplate apiTemplate = new ApiTemplate();

    //클라이언트 가  callBack 을 만들어서 호출한다. -> 템플릿 메서드를
    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return apiTemplate.getExRate(url, new HttpClientApiExecutor(), new ExApiExRateExtractor());
    }
}
