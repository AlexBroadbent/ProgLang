package operator.math;

import eval.ICalculableType;
import eval.Literal;
import operator.IConstants;
import operator.IPrecedence;
import operator.base.UnaryOperator;
import parser.IncomparableTypeException;

/**
 * x++.operator.math
 *
 * @version     31/12/2015
 * @author      Alexander Broadbent
 */
public class SquareRoot extends UnaryOperator {

    @Override
    public String getToken() {
        return IConstants.SQUAREROOT;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.FUNCTION;
    }

    @Override
    public int getType() {
        return ICalculableType.MATH_OPERATOR;
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
