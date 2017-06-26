package com.xchange.service.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Temporary REST controller for test.
 * Created by Amalia Brad.
 */
@RestController
@RequestMapping("")
@Api(value = "Hello", description = "Hello service", produces = MediaType
        .APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class Hello {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hello.class);

    @ApiOperation(value = "Hello")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 400, message = "BAD REQUEST"),
            @ApiResponse(code = 404, message = "NOT FOUND")})
    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void hello() {
        LOGGER.info("Request to Exchange Rate Service!");
    }
}
