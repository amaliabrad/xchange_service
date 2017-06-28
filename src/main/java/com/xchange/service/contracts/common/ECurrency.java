package com.xchange.service.contracts.common;

/**
 * Enumeration for valid currencies.
 * Created by Amalia Brad.
 */
public enum ECurrency {

    USD("USD"),

    JPY("JPY"),

    BGN("BGN"),

    CZK("CZK"),

    DKK("DKK"),

    GBP("GBP"),

    HUF("HUF"),

    PLN("PLN"),

    RON("RON"),

    SEK("SEK"),

    CHF("CHF"),

    NOK("NOK"),

    RUB("RUB"),

    TRY("TRY"),

    AUD("AUD"),

    BRL("BRL"),

    CAD("CAD"),

    CNY("CNY"),

    HKD("HKD"),

    IDR("IDR"),

    ILS("ILS"),

    INR("INR"),

    KRW("KRW"),

    MXN("MXN"),

    MYR("MYR"),

    NZD("NZD"),

    PHP("PHP"),

    SGD("SGD"),

    THB("THB"),

    ZAR("ZAR");


    private String type;

    ECurrency(String type) {
        this.type = type;
    }


    /**
     * Search and returns the ECurrency matching a given type, if any.
     *
     * @param type The type for which we'll return the ECurrency matching it, if any.
     * @return
     */
    public static ECurrency valueForType(String type) {
        ECurrency match = null;

        for (final ECurrency currency : values()) {
            if (currency.getType().equalsIgnoreCase(type)) {
                match = currency;
                break;
            }
        }

        return match;
    }


    public String getType() {
        return type;
    }
}
