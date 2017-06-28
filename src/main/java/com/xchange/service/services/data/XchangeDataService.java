package com.xchange.service.services.data;

import com.xchange.service.contracts.dto.CurrencyRate;
import com.xchange.service.contracts.dto.CurrencyRateTime;

import java.util.List;

/**
 * Data service interface for storing and processing exchange rates.
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
     * Update exchange rates for the last 90 days.
     *
     * @param currencyRateTimeList the updated rates for the last 90 days
     */
    void updateAllRates(List<CurrencyRateTime> currencyRateTimeList);

    /**
     * Fetch daily exchange rates from ECB for a currency.
     *
     * @param currency the currency we want to see the exchange rate for
     * @return the rate of exchange for the current date
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
