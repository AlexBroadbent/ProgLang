package uk.co.alexbroadbent.operator.comparator;

import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.Literal;
import uk.co.alexbroadbent.operator.IConstants;
import uk.co.alexbroadbent.operator.IPrecedence;
import uk.co.alexbroadbent.operator.base.BinaryOperator;

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
