package lexer;

/**
 * x++.parser
 *
 * @author Alexander Broadbent
 * @version 31/10/2015
 */
public interface IToken {

    int WHITESPACE               = 0;
    int BOOLEAN_COMPARATOR       = 1;
    int ASSIGNMENT               = 2;
    int INC_DEC                  = 3;
    int BINARY_8BIT              = 4;
    int DECIMAL                  = 5;
    int NUMBER                   = 6;
    int ARITHMETIC               = 7;
    int GEOMETRIC                = 8;
    int BOOLEAN                  = 9;
    int FUNCTION_DECLARATION     = 10;
    int LOGICAL_OPERATOR         = 11;
    int BITWISE_OPERATOR         = 12;
    int BIT_SHIFT                = 13;
    int MATH_EQUALITY_COMPARATOR = 14;
    int MATH_COMPARATOR          = 15;
    int PARENTHESIS              = 16;
    int FOR_LOOP_ELEMENT         = 17;
    int TEXT                     = 18;
    int VARIABLE_FUNCTION        = 19;
    int CONDITIONAL              = 20;
    int LIST                     = 21;
    int ARG_SEPARATOR            = 22;
    int ARRAY_ACCESS             = 23;

    String WHITESPACE_REGEX               = "\\s";
    String BOOLEAN_COMPARATOR_REGEX       = "\\!\\=|\\=\\=";
    String ASSIGNMENT_REGEX               = "\\=";
    String INC_DEC_REGEX                  = "\\+\\+|\\-\\-";
    String BINARY_8BIT_REGEX              = "[0-1]{8}";
    String DECIMAL_REGEX                  = "[+-]?((\\d*\\.\\d+))";
    String NUMBER_REGEX                   = "[+-]?[0-9]+";
    String ARITHMETIC_REGEX               = "\\+|\\-|\\#";
    String GEOMETRIC_REGEX                = "\\*|/|\\^|\\%";
    String BOOLEAN_REGEX                  = "true|false";
    String FUNCTION_DECLARATION_REGEX     = "func";
    String LOGICAL_COMPARATOR_REGEX       = "\\!|\\&\\&|\\|\\|";
    String BITWISE_OPERATOR_REGEX         = "\\~|\\&|\\||\\$";
    String BIT_SHIFT_OPERATOR_REGEX       = "\\<\\<|\\>\\>";
    String MATH_COMPARATOR_EQUALITY_REGEX = "\\<\\=|\\>\\=";
    String MATH_COMPARATOR_REGEX          = "\\<|\\>";
    String PARENTHESIS_REGEX              = "\\(|\\)";
    String FOR_LOOP_ELEMENT_REGEX         = "(for|in|do)[\\W]";
    String TEXT_REGEX                     = "\"([^\"| ]*)\"|\'([^\'| ]*)\'";
    String VARIABLE_OR_FUNCTION_REGEX     = "\\w(\\w|\\d)*";
    String CONDITIONAL_REGEX              = "\\?|\\:";
    String LIST_REGEX                     = "\\{|\\}";
    String ARG_SEPARATOR_REGEX            = "\\,";
    String ARRAY_ACCESS_REGEX             = "\\[|\\]";

}
