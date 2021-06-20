package parser.stax;

import entity.Tariff;
import entity.Tariffication;
import exception.TariffException;
import parser.AbstractTariffsBuilder;
import parser.TariffXmlTag;
import validation.CustomFileValidator;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;


public class StaxParser extends AbstractTariffsBuilder {
    private XMLInputFactory inputFactory;

    public StaxParser() {
        inputFactory = XMLInputFactory.newFactory();
        tariffs = new ArrayList();
    }

    public StaxParser(ArrayList tariffs) {
        super(tariffs);
    }

    @Override
    public void buildSetTariffs(String filePath) throws TariffException {
        if (!CustomFileValidator.isFileValid(filePath)) {

            throw new TariffException("Invalid filepath " + filePath);
        }
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(new File(filePath))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(TariffXmlTag.TARIFF.getValue())) {
                        Tariff tariff = buildTariff(reader);
                        tariffs.add(tariff);
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new TariffException("Exception " + e);
        } catch (IOException e) {
            throw new TariffException("Exception " + e);
        }
    }

    private Tariff buildTariff(XMLStreamReader reader) throws TariffException, XMLStreamException {
        Tariff tariff = new Tariff();
        Tariff.CallPrices callPrices = tariff.getCallPrices();
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (TariffXmlTag.getTag(name)) {
                        case NAME -> tariff.setName(getXMLText(reader));
                        case PAYROLL -> tariff.setPayroll(new BigDecimal(getXMLText(reader)));
                        case SMS_PRICE -> tariff.setSmsPrice(new BigDecimal(getXMLText(reader)));
                        case OPERATOR -> tariff.setOperator(getXMLText(reader));
                        case CALL_PRICE_INTERNAL -> callPrices.setCallPriceInternal(new BigDecimal(getXMLText(reader)));
                        case CALL_PRICE_EXTERNAL -> callPrices.setCallPriceExternal(new BigDecimal(getXMLText(reader)));
                        case CALL_PRICE_STATIONARY -> callPrices.setCallPriceStationary(new BigDecimal(getXMLText(reader)));
                        case PARAMETERS -> tariff.setParameters(getXMLParameters(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (TariffXmlTag.getTag(name) == TariffXmlTag.TARIFF) {
                        return tariff;
                    }
            }
        }
        throw new TariffException("Error");
    }


    private Tariff.Parameters getXMLParameters(XMLStreamReader reader) throws XMLStreamException, TariffException {
        Tariff.Parameters parameters = new Tariff().new Parameters();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (TariffXmlTag.getTag(name)) {
                        case FIRST_FEE -> parameters.setFirstFee(new BigDecimal(getXMLText(reader)));
                        case FAV_NUMBERS -> parameters.setFavNumbers(Integer.parseInt(getXMLText(reader)));
                        case TARIFFICATION -> parameters.setTariffication(Tariffication.valueOf(getXMLText(reader).toUpperCase()));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (TariffXmlTag.getTag(name) == TariffXmlTag.PARAMETERS) {
                        return parameters;
                    }
            }
        }
        throw new TariffException("Param error");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
