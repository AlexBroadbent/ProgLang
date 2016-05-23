package operator.common;

import eval.IncomparableTypeException;
import eval.Literal;
import eval.NoValueException;
import operator.IPrecedence;
import operator.base.BinaryOperator;

import static operator.IConstants.DIVIDE;

/**
 * x++.operator.common
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class Divide extends BinaryOperator {

    @Override
    public String getToken() {
        return DIVIDE;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.GEOMETRIC;
    }

    @Override
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException, NoValueException {
        try {
            return Double.valueOf(arg1.getValue().toString()) / Double.valueOf(arg2.getValue().toString());
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(),
                    getIncomparableObject(arg1, arg2, getAllowedExecutionTypes()));
        }
    }
}
