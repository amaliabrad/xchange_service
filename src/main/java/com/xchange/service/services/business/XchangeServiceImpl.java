package com.xchange.service.services.business;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xchange.service.contracts.dto.CurrencyRate;
import com.xchange.service.contracts.dto.CurrencyRateTime;
import com.xchange.service.services.data.XchangeDataService;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

/**
 * Created by Amalia Brad.
 */
@Service
public class XchangeServiceImpl implements XchangeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(XchangeServiceImpl.class);

    @Autowired
    XchangeDataService xchangeDataService;

    @Override
    public CurrencyRate fetchDailyRateForCurrency(String currency) {
        if (!StringUtils.isEmpty(currency) && validCurrency(currency)) {
            return xchangeDataService.fetchDailyRateForCurrency(currency);
        }
        //TODO add exception handling
        return null;
    }

    @Override
    public CurrencyRate fetchRateForCurrencyAndTime(String currency, String time) {
        if (!StringUtils.isEmpty(currency) && validCurrency(currency) && validTimeInterval(time)) {
            return xchangeDataService.fetchRateForCurrencyAndTime(currency, time);
        }
        //TODO add exception handling
        return null;
    }

    private boolean validTimeInterval(String time) {
        return true;
    }

    private boolean validCurrency(String currency) {
        return true;
    }

}
