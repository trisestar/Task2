package parser.dom;

import entity.Tariff;
import entity.Tariffication;
import exception.TariffException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import parser.AbstractTariffsBuilder;
import validation.CustomFileValidator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;


public class DomParser extends AbstractTariffsBuilder {
    private final DocumentBuilder docBuilder;

    public DomParser() throws TariffException {
        tariffs = new ArrayList();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new TariffException("Error: " + e);
        }
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String text = node.getTextContent();
        return text;
    }

    public void buildSetTariffs(String filePath) throws TariffException {
        if (!CustomFileValidator.isFileValid(filePath)) {
            throw new TariffException("File invalid: " + filePath);
        }
        Document doc;
        try {
            doc = docBuilder.parse(filePath);
            Element root = doc.getDocumentElement();
            NodeList tariffsList = root.getElementsByTagName("tariff");
            for (int i = 0; i < tariffsList.getLength(); ++i) {
                Element tariffElement = (Element) tariffsList.item(i);
                Tariff tariff = buildTariff(tariffElement);
                tariffs.add(tariff);
            }

        } catch (IllegalArgumentException e) {
            throw new TariffException("IllegalArgumentException " + e);
        } catch (IOException | SAXException e) {
            throw new TariffException("Error: " + e);
        }
    }

    private Tariff buildTariff(Element tariffElem) throws TariffException {
        if (tariffElem == null) {
            throw new TariffException("null tariffElem");
        }
        Tariff tariff = new Tariff();
        tariff.setName(getElementTextContent(tariffElem, "name"));
        tariff.setOperator(getElementTextContent(tariffElem, "operator"));
        var bigDecimalBuff = new BigDecimal(getElementTextContent(tariffElem, "payroll"));
        tariff.setPayroll(bigDecimalBuff);

        Tariff.CallPrices callPrices = tariff.getCallPrices();

        bigDecimalBuff = BigDecimal.valueOf(Double.parseDouble(getElementTextContent(tariffElem, "callPriceInternal")));
        callPrices.setCallPriceInternal(bigDecimalBuff);
        bigDecimalBuff = BigDecimal.valueOf(Double.parseDouble(getElementTextContent(tariffElem, "callPriceExternal")));
        callPrices.setCallPriceExternal(bigDecimalBuff);
        bigDecimalBuff = BigDecimal.valueOf(Double.parseDouble(getElementTextContent(tariffElem, "callPriceStationary")));
        callPrices.setCallPriceStationary(bigDecimalBuff);

        bigDecimalBuff = BigDecimal.valueOf(Double.parseDouble(getElementTextContent(tariffElem, "smsPrice")));
        tariff.setSmsPrice(bigDecimalBuff);

        Tariff.Parameters parameters = tariff.getParameters();
        Element parametersElem = (Element) tariffElem.getElementsByTagName("parameters").item(0);
        parameters.setFavNumbers(Integer.parseInt(getElementTextContent(parametersElem, "favNumbers")));
        parameters.setTariffication(Tariffication.valueOf(getElementTextContent(parametersElem, "tariffication").toUpperCase()));
        bigDecimalBuff = BigDecimal.valueOf(Double.parseDouble(getElementTextContent(parametersElem, "firstFee")));
        parameters.setFirstFee(bigDecimalBuff);


        return tariff;
    }
}
