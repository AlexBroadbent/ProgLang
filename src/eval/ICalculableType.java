package eval;

/**
 * x++.eval
 *
 * @author Alexander Broadbent
 * @version 02/12/2015
 */
public interface ICalculableType {

    int OPERATOR             = 1;
    int FUNCTION             = 2;
    int USER_FUNCTION        = 3;
    int EXPRESSION           = 4;

    int VARIABLE             = 5;
    int LITERAL              = 6;

    int LEFT_PARENTHESIS     = 7;
    int RIGHT_PARENTHESIS    = 8;
    int ARG_SEPARATOR        = 9;
    int LIST_START           = 10;
    int FUNCTION_PLACEHOLDER = 11;

}
