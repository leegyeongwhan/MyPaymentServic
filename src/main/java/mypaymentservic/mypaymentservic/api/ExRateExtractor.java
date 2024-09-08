package mypaymentservic.mypaymentservic.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;

public interface ExRateExtractor {
    BigDecimal extractExRate(String response) throws JsonProcessingException;
}
