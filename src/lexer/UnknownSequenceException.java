package lexer;

/**
 * x++.lexer
 *
 * @author Alexander Broadbent
 * @version 05/01/2016
 */
public class UnknownSequenceException extends Exception {

    private static final String OUTPUT_MSG = "Unknown sequence: %s";


    public UnknownSequenceException(String sequence) {
        super(String.format(OUTPUT_MSG, sequence));
    }

}
