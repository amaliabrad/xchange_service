package com.xchange.service.services.endpoint;

/**
 * Created by Amalia Brad.
 */
public interface EcbClient {
    /**
     * Fetch daily exchange rates from ECB.
     */
    void fetchDailyRates();

    /**
     * Fetch all rates available in the last 90 days from ECB.
     */
    void fetchAllRates();
}
