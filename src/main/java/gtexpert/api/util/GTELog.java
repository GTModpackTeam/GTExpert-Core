package gtexpert.api.util;

import org.apache.logging.log4j.Logger;

public class GTELog {
    public static Logger logger;

    private GTELog() {}

    public static void init(Logger modLogger) {
        logger = modLogger;
    }
}
