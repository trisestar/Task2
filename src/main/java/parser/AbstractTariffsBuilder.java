package parser;

import exception.TariffException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public abstract class AbstractTariffsBuilder {
    public static Logger logger = LogManager.getLogger();
    public ArrayList tariffs;

    public AbstractTariffsBuilder() {
        tariffs = new ArrayList();
    }

    public AbstractTariffsBuilder(ArrayList tariffs) {
        this.tariffs = tariffs;
    }

    public ArrayList getTariffs() {
        logger.info(tariffs.size() + " tariffs has been loaded");
        return this.tariffs;
    }

    public abstract void buildSetTariffs(String filePath) throws TariffException;
}
