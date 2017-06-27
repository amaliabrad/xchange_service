package com.xchange.service.scheduler;

import com.xchange.service.services.business.XchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Amalia Brad.
 */
@Component
public class UpdateRates {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateRates.class);

    @Autowired
    private XchangeService xchangeService;

    /**
     * Schedule update of daily exchange rates every 40 minutes.
     */
    @Scheduled(fixedRate = 2400000)
    public void updateExchangeDailyRates() {
        xchangeService.fetchDailyRates();
        LOGGER.info("Update of daily exchange rates done.");
    }
}
