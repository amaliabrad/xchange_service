package com.xchange.service.services.business;

import com.xchange.service.contracts.dto.CurrencyRate;

/**
 * Created by Amalia Brad.
 */
public interface XchangeService {
    /**
     * Fetch daily exchange rates from ECB.
     */
    void fetchDailyRates();

    /**
     * Fetch daily exchange rates from ECB for a currency.
     *
     * @param currency the currency we want to see the exchange rate
     * @return the rate of exchange for te current date
     */
    CurrencyRate fetchDailyRateForCurrency(String currency);
}
