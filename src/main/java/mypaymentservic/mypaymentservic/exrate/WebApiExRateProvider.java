package mypaymentservic.mypaymentservic.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mypaymentservic.mypaymentservic.api.ApiExecutor;
import mypaymentservic.mypaymentservic.api.ExApiExRateExtractor;
import mypaymentservic.mypaymentservic.api.ExRateExtractor;
import mypaymentservic.mypaymentservic.api.SimpleApiExecutor;
import mypaymentservic.mypaymentservic.paymentservice.ExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class WebApiExRateProvider implements ExRateProvider {

    //클라이언트 가  callBack 을 만들어서 호출한다. -> 템플릿 메서드를
    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return runApiForExRate(url, new SimpleApiExecutor(), new ExApiExRateExtractor());
    }

    //템플릿 메서드 (변하지 않는 작업들)
    private static BigDecimal runApiForExRate(String url, ApiExecutor executor, ExRateExtractor exRateExtractor) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = executor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return exRateExtractor.extractExRate(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
