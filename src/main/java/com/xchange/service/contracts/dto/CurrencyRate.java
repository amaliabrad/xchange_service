package com.xchange.service.contracts.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.math.BigDecimal;

/**
 * Created by Amalia Brad.
 */

public class CurrencyRate {

    @JsonProperty(value = "currency")
    private String currency;
    @JsonProperty(value = "rate")
    private double rate;

    public CurrencyRate(String cur, double value) {
        currency = cur;
        rate = value;
    }

    public CurrencyRate() {
    }

    @JsonGetter(value = "currency")
    public String getCurrency() {
        return currency;
    }

    @JsonSetter(value = "currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonGetter(value = "rate")
    public double getRate() {
        return rate;
    }

    @JsonSetter(value = "rate")
    public void setRate(double rate) {
        this.rate = rate;
    }
}
