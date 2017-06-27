package com.xchange.service.api;

import com.xchange.service.contracts.dto.CurrencyRate;
import com.xchange.service.services.business.XchangeService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.springframework.http.HttpStatus.OK;

/**
 * REST API for fetching exchange rates from ECB.
 * Created by Amalia Brad.
 */
@RestController
@RequestMapping("")
@Api(value = "Converter", description = "Exchange currency", produces = MediaType
        .APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class Converter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Converter.class);

    @Autowired
    XchangeService xchangeService;

    @ApiOperation(value = "Converter")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 400, message = "BAD REQUEST"),
            @ApiResponse(code = 404, message = "NOT FOUND")})
    @RequestMapping(value = "/{currency}/convert", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> fetchExchangeRates(@PathVariable(value = "currency") String currency,
                                                @ApiParam(value = "Date in yyyy-MM-dd format.")
                                                @RequestParam(value = "date", required = false)
                                                @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        LOGGER.info("Request to Exchange CurrencyRate Service!");

        //TODO validate currency, validate date
        CurrencyRate currencyRate = null;
        if (date == null && currency != null) {
            currencyRate = xchangeService.fetchDailyRateForCurrency(currency);
        }
        //TODO add exception handling
        return new ResponseEntity<CurrencyRate>(currencyRate, OK);
    }
}
