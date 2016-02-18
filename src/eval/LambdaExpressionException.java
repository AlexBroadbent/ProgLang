package eval;

/**
 * x++.eval
 *
 * @author      Alexander Broadbent
 * @version     28/12/2015
 */
public class LambdaExpressionException extends Exception {

    public LambdaExpressionException() {
        super();
    }

    public LambdaExpressionException(String msg) {
        super(msg);
    }


    @Override
    public String toString() {
        return getMessage();
    }

}
