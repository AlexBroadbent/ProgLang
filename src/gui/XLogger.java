package gui;

import static gui.IConstants.DEBUG_MODE;
import static gui.IConstants.DEBUG_MODE_DEFAULT;

/**
 * x++.gui
 * <p>
 * Handles logging to the user, can be used to debug log and handle formatted outputs
 * </p>
 * <p>
 * By default, the debug mode is turned on to display all messages. This can be switched
 * using the command 'domain debug false' to hide all log and warning messages, but severe
 * warnings will still be shown.
 * </p>
 *
 * @author Alexander Broadbent
 * @version 05/01/2016
 */
public abstract class XLogger {

    private final static String FORMAT_LOG     = "%s \n";
    private final static String FORMAT_WARNING = "WARNING: %s \n";
    private final static String FORMAT_SEVERE  = "SEVERE: %s \n";

    private static boolean debug = XProperties.getBoolean(DEBUG_MODE, DEBUG_MODE_DEFAULT);


    public static void log(String msg) {
        if (debug)
            System.out.print(String.format(FORMAT_LOG, msg));
    }

    public static void warning(String msg) {
        if (debug)
            System.err.print(String.format(FORMAT_WARNING, msg));
    }

    public static void severe(String msg) {
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
