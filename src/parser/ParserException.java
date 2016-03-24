package parser;

/**
 * x++.parser
 *
 * @author Alexander Broadbent
 * @version 21/03/2016
 */
public class ParserException extends Exception {

    public ParserException() {
        super();
    }

    public ParserException(String msg) {
        super(msg);
    }


    @Override
    public String toString() {
        return getMessage();
    }

}