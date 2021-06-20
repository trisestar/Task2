package parser;

import exception.TariffException;

public enum TariffXmlTag {
    TARIFF("tariff"),
    STARTING_DATE("starting-date"),
    NAME("name"),
    PAYROLL("payroll"),
    SMS_PRICE("smsPrice"),
    OPERATOR("operator"),
    CALL_PRICE_INTERNAL("callPriceInternal"),
    CALL_PRICE_EXTERNAL("callPriceExternal"),
    CALL_PRICE_STATIONARY("callPriceStationary"),
    FIRST_FEE("firstFee"),
    FAV_NUMBERS("favNumbers"),
    TARIFFICATION("tariffication"),
    PARAMETERS("parameters");


    private String value;

    TariffXmlTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TariffXmlTag getTag(String line) throws TariffException {
        if (line == null || line.isEmpty()) {
            throw new TariffException("Empty line");
        }
        for (TariffXmlTag tag : TariffXmlTag.values()) {
            if (tag.value.equalsIgnoreCase(line)) {
                return tag;
            }
        }
        return null;
    }
}
