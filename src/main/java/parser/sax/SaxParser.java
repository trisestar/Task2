package parser.sax;

import exception.TariffErrorHandler;
import exception.TariffException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import parser.AbstractTariffsBuilder;
import validation.CustomFileValidator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;

public class SaxParser extends AbstractTariffsBuilder {
    private XMLReader reader;
    private TariffHandler handler = new TariffHandler();

    public SaxParser() throws TariffException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader  = saxParser.getXMLReader();
        } catch (SAXException | ParserConfigurationException e) {
            throw new TariffException("Error: " + e);
        }
        reader.setErrorHandler(new TariffErrorHandler());
        reader.setContentHandler(handler);
    }

    public SaxParser(ArrayList tariffs) {
        super(tariffs);
    }

    public void buildSetTariffs(String filePath) throws TariffException {
        if (!CustomFileValidator.isFileValid(filePath)) {
            throw new TariffException("File invalid: " + filePath);
        }
        try {
            reader.parse(filePath);
        } catch (NullPointerException e) {
            throw new TariffException("Error: " + e);
        } catch (IOException | SAXException e) {
            throw new TariffException("Error: " + e);
        }
        tariffs = handler.getTariffs();
    }
}
