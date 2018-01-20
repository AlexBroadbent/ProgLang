package uk.co.alexbroadbent.eval;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * x++.parser
 *
 * @author Alexander Broadbent
 * @version 05/01/2016
 */
public class IncomparableTypeException extends Exception {

    private static final String OUTPUT_MSG = "Cannot perform operation, expected %s where the value is of type %s.";


    public IncomparableTypeException(String msg) {
        super(msg);
    }

    public IncomparableTypeException(List<String> expectedTypes, ICalculable actual) {
        super(String.format(OUTPUT_MSG, StringUtils.join(expectedTypes, ", "),
                (actual.getType() == ICalculableType.VARIABLE) ? ((Variable) actual).getValue().getClass().getSimpleName() : actual.getClass().getSimpleName()));
    }

}
