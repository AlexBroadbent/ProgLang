package lexer;

/**
 * JavaCCProgrammingLanguage.parser
 *
 * @author Alexander Broadbent
 * @version 31/10/2015
 */
public interface IToken {

    int    WHITESPACE                     = 0;
    int    BOOLEAN_COMPARATOR             = 1;
    int    ASSIGNMENT                     = 2;
    int    INC_DEC                        = 3;
    int    BINARY_8BIT                    = 4;
    int    DECIMAL                        = 5;
    int    NUMBER                         = 6;
    int    ARITHMETIC                     = 7;
    int    GEOMETRIC                      = 8;
    int    BOOLEAN                        = 9;
    int    FUNCTION                       = 10;
    int    FUNCTION_DECLARATION           = 11;
    int    LOGICAL_OPERATOR               = 12;
    int    BITWISE_OPERATOR               = 13;
    int    BIT_SHIFT                      = 14;
    int    MATH_EQUALITY_COMPARATOR       = 15;
    int    MATH_COMPARATOR                = 16;
    int    LEFT_PARENTHESIS               = 17;
    int    RIGHT_PARENTHESIS              = 18;
    int    FOR_LOOP_ELEMENT               = 19;
    int    TEXT                           = 20;
    int    VARIABLE                       = 21;
    int    IF                             = 22;
    int    ELSE                           = 23;
    int    LIST_START                     = 24;
    int    LIST_END                       = 25;
    int    ARG_SEPARATOR                  = 26;
    int    ARRAY_ACCESS                   = 27;

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
    String FUNCTION_REGEX                 = "sin|cos|tan|exp|ln|log10|sqrt|asin|acos|atan";
    String FUNCTION_DECLARATION_REGEX     = "func";
    String LOGICAL_COMPARATOR_REGEX       = "\\!|\\&\\&|\\|\\|";
    String BITWISE_OPERATOR_REGEX         = "\\~|\\&|\\||\\$";
    String BIT_SHIFT_OPERATOR_REGEX       = "\\<\\<|\\>\\>";
    String MATH_COMPARATOR_EQUALITY_REGEX = "\\<\\=|\\>\\=";
    String MATH_COMPARATOR_REGEX          = "\\<|\\>";
    String LEFT_PAREN_REGEX               = "\\(";
    String RIGHT_PAREN_REGEX              = "\\)";
    String FOR_LOOP_ELEMENT_REGEX         = "(for|in|do)[\\W]";
    String TEXT_REGEX                     = "\"([^\"| ]*)\"|\'([^\'| ]*)\'";
    String VARIABLE_REGEX                 = "[a-zA-Z][a-zA-Z0-9_]*";
    String IF_REGEX                       = "\\?";
    String ELSE_REGEX                     = "\\:";
    String LIST_START_REGEX               = "\\{";
    String LIST_END_REGEX                 = "\\}";
    String ARG_SEPARATOR_REGEX            = "\\,";
    String ARRAY_ACCESS_REGEX             = "\\[|\\]";

}
