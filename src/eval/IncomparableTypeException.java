package eval;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static eval.ICalculableType.VARIABLE;

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

    public IncomparableTypeException(List<String> expectedTypes, ICalculable actual) throws NoValueException {
        super(String.format(OUTPUT_MSG, StringUtils.join(expectedTypes, ", "),
                actual.getType() == VARIABLE ? ((Variable) actual).getValue().getClass().getSimpleName() : actual.getClass().getSimpleName()));
    }

}
