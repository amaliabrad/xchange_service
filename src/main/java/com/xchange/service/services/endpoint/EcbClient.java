package com.xchange.service.services.endpoint;

/**
 * Service used for consuming ECB REST API calls.
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
