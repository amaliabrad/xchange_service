package com.xchange.service.services.data;

import com.xchange.service.contracts.dto.CurrencyRate;
import com.xchange.service.contracts.dto.CurrencyRateTime;

/**
 * Created by Amalia Brad.
 */
public interface XchangeDataService {

    /**
     * Update exchange daily rates for all currencies.
     *
     * @param exchangeRates the updated daily exchange rates
     */
    void updateDailyRates(CurrencyRateTime exchangeRates);

    /**
     * Fetch daily exchange rates from ECB for a currency.
     *
     * @param currency the currency we want to see the exchange rate
     * @return the rate of exchange for te current date
     */
    CurrencyRate fetchDailyRateForCurrency(String currency);
}
