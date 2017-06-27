package com.xchange.service.services.data;

import com.xchange.service.contracts.dto.CurrencyRate;
import com.xchange.service.contracts.dto.CurrencyRateTime;
import org.springframework.stereotype.Service;

/**
 * Created by Amalia Brad.
 */
@Service
public class XchangeDataServiceImpl implements XchangeDataService {

    CurrencyRateTime exchangeRates;

    @Override
    public void updateDailyRates(CurrencyRateTime exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    @Override
    public CurrencyRate fetchDailyRateForCurrency(String currency) {
        CurrencyRate result = exchangeRates.getCurrencyRateList().stream()
                .filter(c -> currency.equals(c.getCurrency()))
                .findAny()
                .orElse(null);
        return result;
    }

    public CurrencyRateTime getExchangeRates() {
        return exchangeRates;
    }
}
