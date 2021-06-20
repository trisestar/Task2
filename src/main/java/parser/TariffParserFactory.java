package parser;

import exception.TariffException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.dom.DomParser;
import parser.sax.SaxParser;
import parser.stax.StaxParser;

public class TariffParserFactory {
    private static final Logger logger = LogManager.getLogger();

    public static AbstractTariffsBuilder createTariffBuilder(String typeParser) throws TariffException {
        if (typeParser == null || typeParser.isEmpty()) {
            throw new TariffException("Parser error");
        }
        TypeParser type = null;
        try {
            type = TypeParser.valueOf(typeParser.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TariffException("Parser error");
        }
        switch (type) {
            case DOM -> {
                return new DomParser();
            }
            case STAX -> {
                return new StaxParser();
            }
            case SAX -> {
                return new SaxParser();
            }
            default -> throw new EnumConstantNotPresentException(
                    type.getDeclaringClass(), type.name());
        }
    }

    private enum TypeParser {
        SAX, STAX, DOM
    }
}