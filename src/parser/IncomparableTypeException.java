package parser;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * x++.parser
 *
 * @author      Alexander Broadbent
 * @version     05/01/2016
 */
public class IncomparableTypeException extends Exception {

    protected static final String OUTPUT_MSG        = "Cannot perform operation, operation requires a value " +
                                                      "of type %s where the value is of type %s.";


    public IncomparableTypeException(String expectedType, String foundType) {
        super(String.format(OUTPUT_MSG, expectedType, foundType));
    }

    public IncomparableTypeException(List<String> expectedTypes, String foundType) {
        super(String.format(OUTPUT_MSG, StringUtils.join(expectedTypes, ", "), foundType));
    }


    @Override
    public String toString() {
        return getMessage();
    }
}
