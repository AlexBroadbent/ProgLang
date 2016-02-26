package operator.comparator;

import eval.ICalculableType;
import eval.Literal;
import operator.IConstants;
import operator.IPrecedence;
import operator.base.BinaryOperator;
import parser.IncomparableTypeException;

/**
 * x++.operator
 *
 * @author      Alexander Broadbent
 * @version     04/01/2016
 */
public class GreaterThan extends BinaryOperator {

    @Override
    public String getToken() {
        return IConstants.GREATER_THAN;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.MATH_COMPARATOR;
    }

    @Override
    public int getType() {
        return ICalculableType.COMPARATOR_OPERATOR;
    }

    @Override
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException {
        try {
            return Double.valueOf(arg1.getValue().toString()) > Double.valueOf(arg2.getValue().toString());
        } catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(),
                    getIncomparableType(arg1.getClass().getSimpleName(), arg2.getClass().getSimpleName(), getAllowedExecutionTypes()));
        }
    }
}