package parser.sax;

import entity.Tariff;
import entity.Tariffication;
import exception.TariffException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import parser.TariffXmlTag;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumSet;


public class TariffHandler extends DefaultHandler {
    private static final String TARIFF = "tariff";
    private static final Logger logger = LogManager.getLogger();
    private final ArrayList tariffs;
    private Tariff current;
    private TariffXmlTag currentXmlTag;
    private final EnumSet<TariffXmlTag> withText;

    public TariffHandler() {
        tariffs = new ArrayList();
        withText = EnumSet.range(TariffXmlTag.NAME, TariffXmlTag.TARIFFICATION);
    }

    public ArrayList getTariffs() {
        return tariffs;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (TARIFF.equals(qName)) {
            current = new Tariff();
        } else {
            TariffXmlTag temp = null;
            try {
                temp = TariffXmlTag.getTag(qName);
            } catch (TariffException e) {
                logger.error("Empty" + e);
            }
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (TARIFF.equals(qName)) {
            tariffs.add(current);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case NAME -> current.setName(data);
                case PAYROLL -> current.setPayroll(new BigDecimal(data));
                case SMS_PRICE -> current.setSmsPrice(new BigDecimal(data));
                case OPERATOR -> current.setOperator(data);
                case CALL_PRICE_INTERNAL -> current.getCallPrices().setCallPriceInternal(new BigDecimal(data));
                case CALL_PRICE_EXTERNAL -> current.getCallPrices().setCallPriceExternal(new BigDecimal(data));
                case CALL_PRICE_STATIONARY -> current.getCallPrices().setCallPriceStationary(new BigDecimal(data));
                case FIRST_FEE -> current.getParameters().setFirstFee(new BigDecimal(data));
                case FAV_NUMBERS -> current.getParameters().setFavNumbers(Integer.parseInt(data));
                case TARIFFICATION -> current.getParameters().setTariffication(Tariffication.valueOf(data.toUpperCase()));
                default -> throw new EnumConstantNotPresentException(
                        currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }

}
