package gui;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static gui.IConstants.DEBUG_MODE;
import static gui.IConstants.LOGGER_NAME;

/**
 * x++.gui
 *
 *  Handles logging to the user, can be used to debug log and handle formatted outputs
 *
 *
 * @author      Alexander Broadbent
 * @version     05/01/2016
 */
public class XLogger {

    protected static boolean debug = DEBUG_MODE;
    protected static Logger logger = Logger.getLogger(LOGGER_NAME);


    public static void log(String msg) {
        if (debug)
            System.out.println(msg);
    }

    public static void severe(String msg) {
        if (debug)
            logger.severe(format(new LogRecord(Level.SEVERE, msg)));
    }

    public static void warning(String msg) {
        if (debug)
            logger.warning(format(new LogRecord(Level.WARNING, msg)));
    }


    public static String format(LogRecord record) {
        return record.getMessage() + "\r\n";
    }

}
