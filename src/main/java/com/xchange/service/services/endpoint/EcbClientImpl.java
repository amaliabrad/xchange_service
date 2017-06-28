package com.xchange.service.services.endpoint;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xchange.service.contracts.dto.CurrencyRateTime;
import com.xchange.service.exception.InternalServerErrorException;
import com.xchange.service.services.data.XchangeDataService;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

/**
 * Service implementation class used for consuming ECB REST API calls.
 * Created by Amalia Brad.
 */
@Service
public class EcbClientImpl implements EcbClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(EcbClientImpl.class);
    private static final String URL_DAILY = "http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
    private static final String URL_ALL = "http://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml";

    @Autowired
    XchangeDataService xchangeDataService;

    @Override
    public void fetchDailyRates() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity(URL_DAILY, String.class);
        String json = XML.toJSONObject(result.getBody()).toString();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(json);
            JsonNode arrayNode = node.get("gesmes:Envelope").get("Cube").get("Cube");
            CurrencyRateTime currencyRateTime = mapper.readValue(arrayNode.toString(), new TypeReference<CurrencyRateTime>() {
            });

            xchangeDataService.updateDailyRates(currencyRateTime);
            LOGGER.info("Successfully retrieved the daily exchange rates. ");
        } catch (IOException e) {
            throw new InternalServerErrorException("There was an error while trying to deserialize the ECB REST API response.");
        }
    }

    @Override
    public void fetchAllRates() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity(URL_ALL, String.class);
        String json = XML.toJSONObject(result.getBody()).toString();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(json);
            JsonNode arrayNode = node.get("gesmes:Envelope").get("Cube").get("Cube");
            List<CurrencyRateTime> currencyRateTimeList = mapper.readValue(arrayNode.toString(), new TypeReference<List<CurrencyRateTime>>() {
            });

            xchangeDataService.updateAllRates(currencyRateTimeList);
            LOGGER.info("Successfully retrieved all the exchange rates not older than 90 days. ");
        } catch (IOException e) {
            throw new InternalServerErrorException("There was an error while trying to deserialize the ECB REST API response.");
        }
    }
}
