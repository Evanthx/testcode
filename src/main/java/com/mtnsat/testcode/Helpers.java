package com.mtnsat.testcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: evanreynolds
 */
public final class Helpers {

    //Hide the utility class constructor
    private Helpers() {};

    public static double stringToDouble(String val, double defaultValue) {
        try {
            return Double.parseDouble(val);
        } catch (NumberFormatException e) {
            Logger logger = LoggerFactory.getLogger(Helpers.class);
            logger.error("Value {} could not be converted to a double", val);
        }

        return defaultValue;
    }

    public static int stringToInt(String val, int defaultValue) {
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            Logger logger = LoggerFactory.getLogger(Helpers.class);
            logger.error("Value {} could not be converted to an integer", val);
        }

        return defaultValue;
    }
}
