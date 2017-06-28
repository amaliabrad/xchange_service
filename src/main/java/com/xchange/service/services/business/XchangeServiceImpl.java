package com.xchange.service.services.business;

import com.xchange.service.contracts.common.ECurrency;
import com.xchange.service.contracts.dto.CurrencyRate;
import com.xchange.service.exception.BadRequestException;
import com.xchange.service.exception.ResourceNotFoundException;
import com.xchange.service.services.data.XchangeDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        CurrencyRate currencyRate;

        if (validCurrency(currency)) {
            currencyRate = xchangeDataService.fetchDailyRateForCurrency(currency.toUpperCase());
        } else {
            throw new BadRequestException("The currency is not valid. Please try again.");
        }

        if (currencyRate == null) {
            throw new ResourceNotFoundException("The requested currency exchange rate was not found. Please try again for a different date.");
        }

        LOGGER.info("Fetched exchange rate for {}", currencyRate.getCurrency());
        return currencyRate;
    }

    @Override
    public CurrencyRate fetchRateForCurrencyAndTime(String currency, String time) {
        CurrencyRate currencyRate;

        if (validCurrency(currency) && validTimeInterval(time)) {
            currencyRate = xchangeDataService.fetchRateForCurrencyAndTime(currency.toUpperCase(), time);
        } else {
            throw new BadRequestException("The currency or time interval is not valid. Please try again.");
        }

        if (currencyRate == null) {
            throw new ResourceNotFoundException("The requested currency exchange rate was not found. Please try again for a different date.");
        }

        LOGGER.info("Fetched exchange rate for {}", currencyRate.getCurrency());
        return currencyRate;
    }

    /**
     * Validate that request time is valid, not older than 90 days.
     *
     * @param time the request time
     * @return true if it is valid, false otherwise
     */
    private boolean validTimeInterval(String time) {
        LocalDate requestTime = null;
        try {
            requestTime = LocalDate.parse(time);
        } catch (Exception e) {
            throw new BadRequestException("The time format is not valid. Please try again.");
        }
        if (requestTime == null) {
            throw new BadRequestException("The time format is not valid. Please try again.");
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
