package operator.math;

import eval.IncomparableTypeException;
import eval.Literal;
import eval.NoValueException;
import operator.IPrecedence;
import operator.base.UnaryOperator;

import static operator.IConstants.ASIN;

/**
 * x++.operator.common
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class ASine extends UnaryOperator {

    @Override
    public String getToken() {
        return ASIN;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.FUNCTION;
    }

    @Override
    public Object execute(Literal arg1) throws IncomparableTypeException, NoValueException {
        try {
            return Math.asin(Double.parseDouble(arg1.getValue().toString()));
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), arg1);
        }
    }

}
