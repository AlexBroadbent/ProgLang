package operator.math;

import eval.IncomparableTypeException;
import eval.Literal;
import operator.IPrecedence;
import operator.base.UnaryOperator;

import static operator.IConstants.SIN;

/**
 * x++.operator.common
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class Sine extends UnaryOperator {

    @Override
    public String getToken() {
        return SIN;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.FUNCTION;
    }

    @Override
    public Object execute(Literal arg1) throws IncomparableTypeException {
        try {
            return Math.sin(Double.parseDouble(arg1.getValue().toString()));
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), arg1);
        }
    }

}
