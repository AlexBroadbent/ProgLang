package operator.math;

import eval.IncomparableTypeException;
import eval.Literal;
import eval.NoValueException;
import operator.IPrecedence;
import operator.base.BinaryOperator;

import static operator.IConstants.POWER;

/**
 * x++.operator.common
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class Power extends BinaryOperator {

    @Override
    public String getToken() {
        return POWER;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.GEOMETRIC;
    }

    @Override
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException, NoValueException {
        try {
            return Math.pow(Double.valueOf(arg1.getValue().toString()), Double.valueOf(arg2.getValue().toString()));
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(),
                    getIncomparableObject(arg1, arg2, getAllowedExecutionTypes()));
        }
    }
}
