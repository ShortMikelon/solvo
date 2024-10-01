package com.github.shortmikelon.solvoassingment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRatesExternalApiResponse {
    private long timeLastUpdate;
    private String baseCode;
    private String targetCode;
    private double conversionResult;
    private double conversionRate;

    @JsonProperty("time_last_update_unix")
    public long getTimeLastUpdate() {
        return timeLastUpdate;
    }

    public void setTimeLastUpdate(long timeLastUpdate) {
        this.timeLastUpdate = timeLastUpdate;
    }

    @JsonProperty("base_code")
    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    @JsonProperty("target_code")
    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    @JsonProperty("conversion_rate")
    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double rate) {
        this.conversionRate = rate;
    }

    @JsonProperty("conversion_result")
    public double getConversionResult() {
        return conversionResult;
    }

    public void setConversionResult(double conversionResult) {
        this.conversionResult = conversionResult;
    }
}
