package com.github.gtexpert.core.api.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.gtexpert.core.api.GTEValues;

public class GTELog {

    private GTELog() {}

    public static Logger logger = LogManager.getLogger(GTEValues.MODNAME);
}
