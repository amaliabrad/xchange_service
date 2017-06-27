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
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by Amalia Brad.
 */
@Service
public class XchangeServiceImpl implements XchangeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(XchangeServiceImpl.class);
    private static final String URL = "http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";

    @Autowired
    XchangeDataService xchangeDataService;

    @Override
    public void fetchDailyRates() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity(URL, String.class);
        String json = XML.toJSONObject(result.getBody()).toString();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(json);
            JsonNode arrayNode = node.get("gesmes:Envelope").get("Cube").get("Cube");
            CurrencyRateTime currencyRateTime = mapper.readValue(arrayNode.toString(), new TypeReference<CurrencyRateTime>() {
            });
            xchangeDataService.updateDailyRates(currencyRateTime);
        } catch (IOException e) {
            //TODO add exception handling
            e.printStackTrace();
        }
    }

    @Override
    public CurrencyRate fetchDailyRateForCurrency(String currency) {
        return xchangeDataService.fetchDailyRateForCurrency(currency);
    }

}
