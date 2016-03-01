
package operator.common;

import eval.Literal;
import operator.base.BinaryOperator;
import parser.IncomparableTypeException;

import static operator.IConstants.DECREMENT_BY;
import static operator.IPrecedence.ASSIGNMENT_OPERATOR;

/**
 * x++.operator.common
 *
 * @author      Alexander Broadbent
 * @version     30/12/2015
 */
public class DecrementBy extends BinaryOperator {

    @Override
    public String getToken() {
        return DECREMENT_BY;
    }

    @Override
    public int getPrecedence() {
        return ASSIGNMENT_OPERATOR;
    }

    @Override
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException {
        try {
            arg1.setValue(Double.valueOf(arg1.getValue().toString()) - Double.valueOf(arg2.getValue().toString()));
            return arg1;
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(),
                    getIncomparableType(arg1.getClass().getSimpleName(), arg2.getClass().getSimpleName(), getAllowedExecutionTypes()));
        }
    }

}
