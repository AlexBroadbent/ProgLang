package operator.math;

import eval.Literal;
import operator.IConstants;
import operator.IPrecedence;
import operator.base.UnaryOperator;
import parser.IncomparableTypeException;

/**
 * x++.operator.math
 *
 * @author Alexander Broadbent
 * @version 31/12/2015
 */
public class SquareRoot extends UnaryOperator {

    @Override
    public String getToken() {
        return IConstants.SQUARE_ROOT;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.FUNCTION;
    }

    @Override
    public Object execute(Literal arg1) throws IncomparableTypeException {
        try {
            return Math.sqrt(Double.parseDouble(arg1.getValue().toString()));
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), arg1.getClass().getSimpleName());
        }
    }

}
