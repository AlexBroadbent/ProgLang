package parser;

/**
 * x++.parser.exception
 *
 * @author Alexander Broadbent
 * @version 02/12/2015
 */
public class ExpressionException extends Exception {

    public ExpressionException() {
        super();
    }

    public ExpressionException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return getMessage();
    }

}
