package eval;

/**
 * LazyLanguage.eval
 *
 * @version     02/12/2015
 * @author      Alexander Broadbent
 */
public interface ICalculableType {

    int BITWISE_OPERATOR                = 1;
    int COMMON_OPERATOR                 = 2;
    int COMPARATOR_OPERATOR             = 3;
    int EQUALITY_OPERATOR               = 4;
    int FUNCTION_OPERATOR               = 5;
    int MATH_OPERATOR                   = 6;
    int CONDITIONAL_OPERATOR            = 7;

    int LEFT_PARENTHESIS                = 10;
    int RIGHT_PARENTHESIS               = 11;

    int LITERAL                         = 20;
    int ASSIGNMENT                      = 21;
    int VARIABLE                        = 22;

}
