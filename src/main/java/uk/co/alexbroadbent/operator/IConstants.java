package uk.co.alexbroadbent.operator;

/**
 * x++.operator
 * <p>
 * Tokens for operators
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public interface IConstants {

    String ARG_SEPARATOR      = ",";
    String LEFT_PARENTHESIS   = "(";
    String RIGHT_PARENTHESIS  = ")";
    String ARRAY_ACCESS_START = "[";
    String ARRAY_ACCESS_END   = "]";

    String PLUS     = "+";
    String MINUS    = "-";
    String DIVIDE   = "/";
    String MULTIPLY = "*";
    String POWER    = "^";
    String MOD      = "%";
    String CONCAT   = "#";

    String BITWISE_AND = "&";
    String BITWISE_OR  = "|";
    String BITWISE_XOR = "$";
    String LEFT_SHIFT  = "<<";
    String RIGHT_SHIFT = ">>";
    String BITWISE_NOT = "~";

    String LOGICAL_NOT = "!";
    String LOGICAL_AND = "&&";
    String LOGICAL_OR  = "||";

    String INCREMENT = "++";
    String DECREMENT = "--";

    String ASSIGNMENT         = "=";
    String GREATER_THAN       = ">";
    String GREATER_THAN_EQUAL = ">=";
    String LESS_THAN          = "<";
    String LESS_THAN_EQUAL    = "<=";
    String EQUALITY           = "==";
    String INEQUALITY         = "!=";

    String CONDITIONAL      = "?";
    String CONDITIONAL_ELSE = ":";
    String LIST_START       = "{";
    String LIST_END         = "}";
    String FOR_LOOP         = "for";
    String IN               = "in";
    String DO               = "do";

    /*
        Functions
     */
    String EXPONENTIAL = "exp";
    String SQUARE_ROOT = "sqrt";
    String LN          = "ln";
    String LOG_10      = "log10";
    String SIN         = "sin";
    String COS         = "cos";
    String TAN         = "tan";
    String ASIN        = "asin";
    String ACOS        = "acos";
    String ATAN        = "atan";

    String FUNC   = "func";
    String SUM    = "sum";
    String MAX    = "max";
    String RANDOM = "random";
    String ROUND  = "round";
    String LIST   = "list";
    String HEAD   = "head";
    String TAIL   = "tail";
    String CONS   = "cons";
    String SIZE   = "size";
    String EMPTY  = "empty";

}
