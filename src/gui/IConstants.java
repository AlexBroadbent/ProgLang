package gui;

import operator.IConstants.Angle;

/**
 * x++
 *
 * @author Alexander Broadbent
 * @version 05/01/2016
 */
public interface IConstants {

    String LOGGER_NAME    = "x++";
    String LOGGER_VERSION = "1.3";
    String LINE_START     = "> ";

    String DOMAIN             = "domain";
    String LIST               = "list";
    String VARIABLES          = "variables";
    String OPERATORS          = "operators";
    String FUNCTIONS          = "functions";
    String FREE               = "free";
    String RESET              = "reset";
    String VARIABLE           = "variable";
    String DEBUG              = "debug";
    String ANGLE              = "angle";
    String DECIMAL            = "decimal";
    String EXIT               = "exit";
    String APP_PROP_FILE_NAME = "application.properties";
    String DEF_PROP_FILE_NAME = "default.properties";

    /*
            User Function Storage
     */
    String SAVE  = "save";
    String LOAD  = "load";
    String SAVED = "saved";
    String ALL   = "all";
    String VIEW  = "view";

    /*
            XProperties
     */
    String  DEBUG_MODE             = "x.debug_mode";
    Boolean DEBUG_MODE_DEFAULT     = Boolean.TRUE;
    String  ANGLE_MODE             = "x.angle_mode";
    Angle   ANGLE_MODE_DEFAULT     = Angle.RADIANS;
    String  DECIMAL_PLACES         = "x.decimal_places";
    Integer DECIMAL_PLACES_DEFAULT = -1;

}
