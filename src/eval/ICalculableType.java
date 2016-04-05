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

    int VARIABLE             = 4;
    int LITERAL              = 5;

    int LEFT_PARENTHESIS     = 6;
    int RIGHT_PARENTHESIS    = 7;
    int ARG_SEPARATOR        = 8;
    int LIST_START           = 9;
    int FUNCTION_PLACEHOLDER = 10;

}
