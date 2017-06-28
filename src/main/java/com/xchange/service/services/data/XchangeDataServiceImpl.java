package com.xchange.service.services.data;

import com.xchange.service.contracts.dto.CurrencyRate;
import com.xchange.service.contracts.dto.CurrencyRateTime;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Amalia Brad.
 */
@Service
public class XchangeDataServiceImpl implements XchangeDataService {

    CurrencyRateTime exchangeRates;
    List<CurrencyRateTime> currencyRateTimeList;

    @Override
    public void updateDailyRates(CurrencyRateTime exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    @Override
    public void updateAllRates(List<CurrencyRateTime> currencyRateTimeList) {
        this.currencyRateTimeList = currencyRateTimeList;
    }

    @Override
    public CurrencyRate fetchDailyRateForCurrency(String currency) {
        CurrencyRate result = exchangeRates.getCurrencyRateList().stream()
                .filter(c -> currency.equals(c.getCurrency()))
                .findAny()
                .orElse(null);
        return result;
    }

    @Override
    public CurrencyRate fetchRateForCurrencyAndTime(String currency, String time) {
        CurrencyRate result = null;
        CurrencyRateTime rateTime = currencyRateTimeList.stream()
                .filter(currencyRateTime -> time.equals(currencyRateTime.getTime()))
                .findAny()
                .orElse(null);
        if (rateTime != null) {
            result = rateTime.getCurrencyRateList().stream()
                    .filter(c -> currency.equals(c.getCurrency()))
                    .findAny()
                    .orElse(null);
        }
        return result;
    }

    public CurrencyRateTime getExchangeRates() {
        return exchangeRates;
    }
}
