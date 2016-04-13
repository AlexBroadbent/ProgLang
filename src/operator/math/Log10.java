package operator.math;

import eval.IncomparableTypeException;
import eval.Literal;
import operator.IConstants;
import operator.IPrecedence;
import operator.base.UnaryOperator;

/**
 * x++.operator.math
 *
 * @author Alexander Broadbent
 * @version 04/01/2016
 */
public class Log10 extends UnaryOperator {

    @Override
    public String getToken() {
        return IConstants.LOG_10;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.FUNCTION;
    }

    @Override
    public Object execute(Literal arg1) throws IncomparableTypeException {
        try {
            return Math.log10(Double.valueOf(arg1.getValue().toString()));
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), arg1.getClass().getSimpleName());
        }
    }

}
