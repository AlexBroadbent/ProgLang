package operator.common;

import eval.IncomparableTypeException;
import eval.Literal;
import eval.NoValueException;
import operator.IConstants;
import operator.IPrecedence;
import operator.base.UnaryOperator;

/**
 * x++.operator.common
 *
 * @author Alexander Broadbent
 * @version 30/12/2015
 */
public class Increment extends UnaryOperator {

    @Override
    public String getToken() {
        return IConstants.INCREMENT;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.UNARY;
    }

    @Override
    public Object execute(Literal arg1) throws IncomparableTypeException, NoValueException {
        try {
            return Double.valueOf(arg1.getValue().toString()) + 1d;
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), arg1);
        }
    }
}
