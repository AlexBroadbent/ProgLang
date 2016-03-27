package operator.conditional;

import eval.ICalculable;
import operator.IConstants;
import operator.IOperator;
import operator.IPrecedence;
import operator.base.NullaryOperator;

import java.util.List;
import java.util.Stack;

/**
 * x++.operator.conditional
 *
 * @author Alexander Broadbent
 * @version 08/01/2016
 */
public class ConditionalElse extends NullaryOperator {

    @Override
    public String getToken() {
        return IConstants.CONDITIONAL_ELSE;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.CONDITIONAL;
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        while (!operatorStack.isEmpty() && !operatorStack.peek().getToken().equals(IConstants.CONDITIONAL))
            postfix.add(operatorStack.pop());
    }

}
