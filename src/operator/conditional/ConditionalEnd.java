package operator.conditional;

import eval.ICalculable;
import eval.ICalculableType;
import operator.IConstants;
import operator.IOperator;
import operator.IPrecedence;
import operator.base.NullaryOperator;

import java.util.List;
import java.util.Stack;

/**
 * x++.operator.conditional
 *
 * @author      Alexander Broadbent
 * @version     08/01/2016
 */
public class ConditionalEnd extends NullaryOperator {

    @Override
    public String getToken() {
        return IConstants.CONDITIONAL_END;
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
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {

    }

}
