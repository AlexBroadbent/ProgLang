package eval;

/**
 * x++.eval
 *
 * @version     02/12/2015
 * @author      Alexander Broadbent
 */
public interface ICalculableType {

    int OPERATOR                        = 1;
    int FUNCTION                        = 2;
    int USER_FUNCTION = 3;

    int LEFT_PARENTHESIS                = 5;
    int RIGHT_PARENTHESIS               = 6;
    int ARG_SEPARATOR                   = 7;
    int LIST_START                      = 8;
    int FUNCTION_PLACEHOLDER = 9;

    int LITERAL                         = 10;
    int VARIABLE                        = 11;

}
