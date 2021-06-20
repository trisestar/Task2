package main;

import exception.TariffException;
import parser.AbstractTariffsBuilder;
import parser.TariffParserFactory;


public class Main {
    public static void main(String... args) throws TariffException {
        String type = "sax";
        AbstractTariffsBuilder builder = TariffParserFactory.createTariffBuilder(type);
        builder.buildSetTariffs("src\\main\\resources\\xml_data\\tariffs.xml");
        builder.getTariffs().stream().forEach(elem -> System.out.println(elem));

    }
}

