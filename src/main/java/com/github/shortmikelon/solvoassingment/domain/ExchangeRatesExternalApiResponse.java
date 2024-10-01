package com.github.shortmikelon.solvoassingment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
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

    @JsonProperty("base_code")
    public String getBaseCode() {
        return baseCode;
    }

    @JsonProperty("target_code")
    public String getTargetCode() {
        return targetCode;
    }

    @JsonProperty("conversion_rate")
    public double getConversionRate() {
        return conversionRate;
    }

    @JsonProperty("conversion_result")
    public double getConversionResult() {
        return conversionResult;
    }

}
