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
public class ConditionalElse extends NullaryOperator {

    @Override
    public String getToken() {
        return IConstants.CONDITIONAL_ELSE;
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
        // Push all operators onto the postfix
        while (!operatorStack.isEmpty() && !operatorStack.peek().getToken().equals(IConstants.CONDITIONAL))
            postfix.add(operatorStack.pop());

        /*
                in the case of:
                    (x < y+3) ? x + 1/2 : (2/3)*y
                all literals will be pushed, so at the else token, operators are pushed
                to start a new calculation; push until conditional token is found

                    xy3+< x12/+ 23/y*
         */
    }

}
