package com.xchange.service.contracts.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

/**
 * Dto holding information about the currency exchange rate history.
 * Created by Amalia Brad.
 */
public class CurrencyRateTime {

    @JsonProperty(value = "time")
    private String time;

    @JsonProperty(value = "Cube")
    List<CurrencyRate> currencyRateList;

    public CurrencyRateTime() {
    }

    @JsonProperty(value = "time")
    public String getTime() {
        return time;
    }

    @JsonProperty(value = "time")
    public void setTime(String time) {
        this.time = time;
    }

    @JsonGetter(value = "Cube")
    public List<CurrencyRate> getCurrencyRateList() {
        return currencyRateList;
    }

    @JsonSetter(value = "Cube")
    public void setCurrencyRateList(List<CurrencyRate> currencyRateList) {
        this.currencyRateList = currencyRateList;
    }
}
