package gui;

import static gui.IConstants.DEBUG_MODE;

/**
 * x++.gui
 * <p>
 * Handles logging to the user, can be used to debug log and handle formatted outputs
 *
 * @author Alexander Broadbent
 * @version 05/01/2016
 */
public class XLogger {

    private final static String FORMAT_LOG     = "%s \n";
    private final static String FORMAT_WARNING = "WARNING: %s \n";
    private final static String FORMAT_SEVERE  = "SEVERE: %s \n";
    private static boolean debug = DEBUG_MODE;

    public static void log(String msg) {
        if (debug)
            System.out.print(String.format(FORMAT_LOG, msg));
    }

    public static void warning(String msg) {
        if (debug)
            System.err.print(String.format(FORMAT_WARNING, msg));
    }

    public static void severe(String msg) {
        if (debug)
            System.err.print(String.format(FORMAT_SEVERE, msg));
    }


    public static void setDebug(boolean debug) {
        XLogger.debug = debug;
    }


    public static String getLogFormat() {
        return FORMAT_LOG;
    }

    public static String getWarningFormat() {
        return FORMAT_WARNING;
    }

    public static String getSevereFormat() {
        return FORMAT_SEVERE;
    }
}
