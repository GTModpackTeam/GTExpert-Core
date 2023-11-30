package gtexpert.api.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gtexpert.api.GTEValues;

public class GTELog {

    private GTELog() {}

    public static Logger logger = LogManager.getLogger(GTEValues.MODNAME);
}
