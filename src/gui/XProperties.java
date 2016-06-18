package gui;

import operator.IConstants.Angle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static gui.IConstants.*;

/**
 * ProgLang.gui
 * <p>
 * Singleton approach to have a config file, with get and set methods
 *
 * @author Alexander Broadbent
 * @version 14/06/2016
 */
public abstract class XProperties {

    private final static String MSG_LOAD   = "Cannot load properties file, default config will be used.";
    private final static String MSG_SAVE   = "Cannot save to properties file.";
    private static final String MSG_CREATE = "Cannot create a properties file, default settings will be used.";

    private static Properties properties;


    public static String getString(String key, String defaultValue) {
        if (properties == null)
            load();

        return properties.getProperty(key, defaultValue);
    }

    public static Boolean getBoolean(String key, Boolean defaultValue) {
        if (properties == null)
            load();

        return Boolean.parseBoolean(properties.getProperty(key, String.valueOf(defaultValue)));
    }

    public static Angle getAngle(String key, Angle defaultValue) {
        if (properties == null)
            load();

        return Angle.get(properties.getProperty(key, defaultValue.getValue()));
    }

    public static Integer getInteger(String key, Integer defaultValue) {
        if (properties == null)
            load();

        return Integer.parseInt(properties.getProperty(key, String.valueOf(defaultValue)));
    }


    public static void setProperty(String key, String value) {
        if (properties == null)
            load();

        properties.setProperty(key, value);
        save();
    }


    /**
     * Load the config stored in files
     */
    private static void load() {
        try {
            File def = new File(DEF_PROP_FILE_NAME);
            File app = new File(APP_PROP_FILE_NAME);
            if (!(def.exists() && app.exists()))
                create();

            FileInputStream in = new FileInputStream(def);
            Properties defaultProperties = new Properties();
            defaultProperties.load(in);
            in.close();

            properties = new Properties(defaultProperties);
            in = new FileInputStream(app);
            properties.load(in);
            in.close();
        }
        catch (IOException e) {
            XLogger.severe(MSG_LOAD);
        }
    }

    /**
     * Save the properties to the application properties file
     */
    private static void save() {
        try {
            FileOutputStream out = new FileOutputStream(APP_PROP_FILE_NAME);
            properties.store(out, null);
            out.close();
        }
        catch (IOException e) {
            XLogger.severe(MSG_SAVE);
        }
    }


    /**
     * Create the default and application property files with the default values
     * in both.
     */
    public static void create() {
        try {
            Properties p = new Properties();
            FileOutputStream o = new FileOutputStream(DEF_PROP_FILE_NAME);
            FileOutputStream a = new FileOutputStream(APP_PROP_FILE_NAME);

            // write default properties
            p.setProperty(DEBUG_MODE, DEBUG_MODE_DEFAULT.toString());
            p.setProperty(ANGLE_MODE, String.valueOf(ANGLE_MODE_DEFAULT));

            p.store(o, null);
            p.store(a, null);

            o.close();
            a.close();
        }
        catch (IOException e) {
            XLogger.severe(MSG_CREATE);
        }
    }

}
