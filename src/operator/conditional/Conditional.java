package operator.conditional;

import eval.ICalculableType;
import eval.Literal;
import operator.IConstants;
import operator.IPrecedence;
import operator.base.TernaryOperator;
import parser.IncomparableTypeException;

/**
 * x++.operator.conditional
 *
 * @author      Alexander Broadbent
 * @version     08/01/2016
 */
public class Conditional extends TernaryOperator {

    @Override
    public String getToken() {
        return IConstants.CONDITIONAL;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.CONDITIONAL_EXPRESSION;
    }

    @Override
    public int getType() {
        return ICalculableType.CONDITIONAL_OPERATOR;
    }

    @Override
    public Object execute(Literal arg1, Literal arg2, Literal arg3) throws IncomparableTypeException {
        return ((Boolean) arg1.getValue()) ? arg2 : arg3;
    }

}