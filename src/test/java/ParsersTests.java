import entity.Tariff;
import entity.Tariffication;
import exception.TariffException;
import org.testng.Assert;
import org.testng.annotations.Test;
import parser.AbstractTariffsBuilder;
import parser.TariffParserFactory;
import parser.dom.DomParser;
import parser.sax.SaxParser;
import parser.stax.StaxParser;

import java.math.BigDecimal;
import java.util.List;

public class ParsersTests {

    @Test
    public void test() throws TariffException {

        Tariff tariff = new Tariff();
        tariff.setName("NoLimits");
        tariff.setOperator("MTS");
        tariff.setPayroll(new BigDecimal(30));
        tariff.setSmsPrice(new BigDecimal(0.3));
        Tariff.CallPrices callPrices = tariff.getCallPrices();
        callPrices.setCallPriceInternal(new BigDecimal(0));
        callPrices.setCallPriceExternal(new BigDecimal(0.3));
        callPrices.setCallPriceStationary(new BigDecimal(0.3));
        Tariff.Parameters parameters = tariff.getParameters();
        parameters.setFavNumbers(0);
        parameters.setTariffication(Tariffication.MINUTE);
        parameters.setFirstFee(new BigDecimal(5));

        //sax
        AbstractTariffsBuilder builder = TariffParserFactory.createTariffBuilder("sax");
        builder.buildSetTariffs("src\\main\\resources\\xml_data\\tariffs.xml");
        List<Tariff> actual = builder.getTariffs();
        Tariff actualTariff = new Tariff();
        actualTariff = actual.get(2);

        Assert.assertTrue(actualTariff.equals(tariff));

        //stax
        builder = TariffParserFactory.createTariffBuilder("stax");
        builder.buildSetTariffs("src\\main\\resources\\xml_data\\tariffs.xml");
        actual = builder.getTariffs();
        actualTariff = actual.get(2);

        Assert.assertTrue(actualTariff.equals(tariff));

        //dom
        builder = TariffParserFactory.createTariffBuilder("dom");
        builder.buildSetTariffs("src\\main\\resources\\xml_data\\tariffs.xml");
        actual = builder.getTariffs();
        actualTariff = actual.get(2);

        Assert.assertTrue(actualTariff.equals(tariff));

    }



}
