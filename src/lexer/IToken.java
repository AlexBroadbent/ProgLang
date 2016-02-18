package lexer;

/**
 * JavaCCProgrammingLanguage.parser
 *
 * TODO: Shorten to groups: Bitwise operations, Boolean comparators, Assignments, Brackets
 *
 * @author      Alexander Broadbent
 * @version     31/10/2015
 */
public interface IToken {

    int WHITESPACE                              = -1;
    int EPSILON                                 = 0;
    int ARITHMETIC                              = 1;
    int GEOMETRIC                               = 2;
    int FUNCTION                                = 3;
    int BOOLEAN_COMPARATOR                      = 4;
    int MATH_COMPARATOR                         = 6;
    int LEFT_PARENTHESIS                        = 7;
    int RIGHT_PARENTHESIS                       = 8;
    int BINARY_8BIT                             = 9;
    int DECIMAL                                 = 10;
    int NUMBER                                  = 11;
    int TEXT                                    = 12;
    int VARIABLE                                = 13;
    int BOOLEAN                                 = 14;
    int LAMBDA                                  = 15;
    int OPTIONAL_VAR                            = 16;
    int REST_VAR                                = 17;
    int BITWISE_OPERATOR                        = 18;
    int BIT_SHIFT                               = 19;
    int INC_DEC                                 = 20;
    int ASSIGNMENT_INC_DEC                      = 21;
    int LOGICAL_OPERATOR                        = 22;   // Logical AND/OR/NOT
    int ASSIGNMENT                              = 24;
    int IF                                      = 25;
    int ELSE                                    = 26;
    int END                                     = 27;


    String WHITESPACE_REGEX                     = "\\s";
    String ARITHMETIC_REGEX                     = "[+-]";
    String GEOMETRIC_REGEX                      = "[*|/|\\^|\\%]";
    String FUNCTION_REGEX                       = "sin|cos|tan|exp|ln|log10|sqrt|asin|acos|atan";
    String BOOLEAN_COMPARATOR_REGEX             = "\\!\\=|\\=\\=";
    String MATH_COMPARATOR_REGEX                = "\\<|\\>|\\<\\=|\\>\\=";
    String LEFT_PAREN_REGEX                     = "\\(";
    String RIGHT_PAREN_REGEX                    = "\\)";
    String BINARY_8BIT_REGEX                    = "[0-1]{8}";
    String NUMBER_REGEX                         = "[0-9]+,?";
    String DECIMAL_REGEX                        = "[+-]?((\\d*\\.\\d+))";
    String TEXT_REGEX                           = "\".*\"|\\'.*\\'";
    String VARIABLE_REGEX                       = "[a-zA-Z][a-zA-Z0-9_]*";
    String BOOLEAN_REGEX                        = "true|false|TRUE|FALSE";
    String LAMBDA_REGEX                         = "lambda";
    String OPTIONAL_VAR_REGEX                   = "&optional";
    String REST_VAR_REGEX                       = "&rest";
    String BITWISE_OPERATOR_REGEX               = "\\~|\\&|\\||\\$";
    String LOGICAL_COMPARATOR_REGEX             = "\\!|\\&\\&|\\|\\|";
    String BIT_SHIFT_OPERATOR_REGEX             = "\\<\\<|\\>\\>";
    String INC_DEC_REGEX                        = "\\+\\+|\\-\\-";
    String ASSIGNMENT_INC_DEC__REGEX            = "\\+\\=|\\-\\=|\\*\\=|\\/\\=";
    String ASSIGNMENT_REGEX                     = "\\=";
    String IF_REGEX                             = "\\?";
    String ELSE_REGEX                           = "\\:";
    String END_REGEX                            = "\\;";

}
