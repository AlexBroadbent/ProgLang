package operator.comparator;

import eval.IncomparableTypeException;
import eval.Literal;
import operator.IConstants;
import operator.IPrecedence;
import operator.base.BinaryOperator;

/**
 * x++.operator
 *
 * @author Alexander Broadbent
 * @version 04/01/2016
 */
public class GreaterThanEqual extends BinaryOperator {

    @Override
    public String getToken() {
        return IConstants.GREATER_THAN_EQUAL;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.MATH_COMPARATOR;
    }

    @Override
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException {
        try {
            return Double.valueOf(arg1.getValue().toString()) >= Double.valueOf(arg2.getValue().toString());
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(),
                    getIncomparableObject(arg1, arg2, getAllowedExecutionTypes()));
        }
    }
}
