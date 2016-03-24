package operator.common;

import eval.Literal;
import operator.IConstants;
import operator.IPrecedence;
import operator.base.UnaryOperator;
import parser.IncomparableTypeException;

/**
 * x++.operator.common
 *
 * @author Alexander Broadbent
 * @version 30/12/2015
 */
public class Decrement extends UnaryOperator {

    @Override
    public String getToken() {
        return IConstants.DECREMENT;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.UNARY;
    }

    @Override
    public Object execute(Literal arg1) throws IncomparableTypeException {
        try {
            return Double.valueOf(arg1.getValue().toString()) - 1d;
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), arg1.getClass().getSimpleName());
        }
    }

}
