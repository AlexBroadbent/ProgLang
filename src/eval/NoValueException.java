package eval;

/**
 * ProgLang.eval
 *
 * @author Alexander Broadbent
 * @version 21/05/2016
 */
public class NoValueException extends Exception {

    private static final String MSG = "The variable, %s, has no value";

    public NoValueException(String variableName) {
        super(String.format(MSG, variableName));
    }

}
