package eval;

/**
 * x++.eval
 *
 * @author Alexander Broadbent
 * @version 14/03/2016
 */
public class InvalidFunctionException extends Exception {

    public InvalidFunctionException() {
        super();
    }

    public InvalidFunctionException(String msg) {
        super(msg);
    }


    @Override
    public String toString() {
        return this.getMessage();
    }
}
