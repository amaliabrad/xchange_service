package com.xchange.service.services.business;

import com.xchange.service.contracts.common.ECurrency;
import com.xchange.service.contracts.dto.CurrencyRate;
import com.xchange.service.services.data.XchangeDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Service class implementation for handling exchange rate related functionality.
 * Created by Amalia Brad.
 */
@Service
public class XchangeServiceImpl implements XchangeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(XchangeServiceImpl.class);

    @Autowired
    XchangeDataService xchangeDataService;

    @Override
    public CurrencyRate fetchDailyRateForCurrency(String currency) {
        if (validCurrency(currency)) {
            return xchangeDataService.fetchDailyRateForCurrency(currency.toUpperCase());
        }
        //TODO add exception handling
        return null;
    }

    @Override
    public CurrencyRate fetchRateForCurrencyAndTime(String currency, String time) {
        if (validCurrency(currency) && validTimeInterval(time)) {
            return xchangeDataService.fetchRateForCurrencyAndTime(currency.toUpperCase(), time);
        }
        //TODO add exception handling
        return null;
    }

    /**
     * Validate that request time is valid, not older than 90 days.
     *
     * @param time the request time
     * @return true if it is valid, false otherwise
     */
    private boolean validTimeInterval(String time) {
        LocalDate requestTime = LocalDate.parse(time);
        if (requestTime == null) {
            //TODO exception invalid date
        }
        LocalDate now = LocalDate.now();
        long daysBetween = DAYS.between(requestTime, now);
        return daysBetween <= 90;
    }

    /**
     * Validate currency from request.
     *
     * @param currency the currency to be validated
     * @return true if it is valid, false otherwise
     */
    private boolean validCurrency(String currency) {
        if (StringUtils.isEmpty(currency)) {
            return false;
        }
        ECurrency eCurrency = ECurrency.valueForType(currency.toUpperCase());
        return (eCurrency != null);
    }

}
