package gtexpert.api.util;

import gtexpert.api.GTEValues;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GTELog {

    private GTELog() {}

    public static Logger logger = LogManager.getLogger(GTEValues.MODNAME);
}
