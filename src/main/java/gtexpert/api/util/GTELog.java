package gtexpert.api.util;

import gtexpert.api.GTEValues;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GTELog {
    public static Logger logger = LogManager.getLogger(GTEValues.MODNAME);

    private GTELog() {}

    public static void init(Logger modLogger) {
        logger = modLogger;
    }
}
