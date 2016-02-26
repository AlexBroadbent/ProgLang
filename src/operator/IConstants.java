package operator;

/**
 * LazyLanguage.operator
 *
 *  Tokens for operators
 *
 * @version     01/12/2015
 * @author      Alexander Broadbent
 */
public interface IConstants {

    String ARG_SEPARATOR        = ",";
    String LEFT_PARENTHESIS     = "(";
    String RIGHT_PARENTHESIS    = ")";

    String PLUS                 = "+";
    String MINUS                = "-";
    String DIVIDE               = "/";
    String MULTIPLY             = "*";
    String POWER                = "^";
    String MOD                  = "%";

    String BITWISE_AND          = "&";
    String BITWISE_OR           = "|";
    String BITWISE_XOR          = "$";
    String LEFT_SHIFT           = "<<";
    String RIGHT_SHIFT          = ">>";
    String BITWISE_NOT          = "~";

    String LOGICAL_NOT          = "!";
    String LOGICAL_AND          = "&&";
    String LOGICAL_OR           = "||";
    String NOT                  = "!";
    String INCREMENT            = "++";

    String DECREMENT            = "--";
    String INCREMENT_BY         = "+=";
    String DECREMENT_BY         = "-=";
    String MULTIPLY_BY          = "*=";

    String DIVIDE_BY            = "/=";
    String EXPONENTIAL          = "exp";
    String SQUAREROOT           = "sqrt";
    String LN                   = "ln";
    String LOG_10               = "log10";

    String SIN                  = "sin";
    String COS                  = "cos";
    String TAN                  = "tan";
    String ASIN                 = "asin";
    String ACOS                 = "acos";
    String ATAN                 = "atan";

    String CONCATENATION        = "#";
    String ASSIGNMENT           = "=";

    String GREATER_THAN         = ">";
    String GREATER_THAN_EQUAL   = ">=";
    String LESS_THAN            = "<";
    String LESS_THAN_EQUAL      = "<=";
    String EQUALITY             = "==";
    String INEQUALITY           = "!=";

    String CONDITIONAL          = "?";
    String CONDITIONAL_ELSE     = ":";
    String CONDITIONAL_END      = ";";

}