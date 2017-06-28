package com.xchange.service.services.business;

import com.xchange.service.contracts.dto.CurrencyRate;

/**
 * Service for handling exchange rate related functionality.
 * Created by Amalia Brad.
 */
public interface XchangeService {

    /**
     * Fetch daily exchange rates from ECB for a currency.
     *
     * @param currency the currency we want to see the exchange rate
     * @return the rate of exchange for te current date
     */
    CurrencyRate fetchDailyRateForCurrency(String currency);

    /**
     * Fetch exchange rate from ECB for a currency on a given date.
     *
     * @param currency the currency we want to see the exchange rate for
     * @param time     the requested time
     * @return the rate of exchange for the given date
     */
    CurrencyRate fetchRateForCurrencyAndTime(String currency, String time);
}
