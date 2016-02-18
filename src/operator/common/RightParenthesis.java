package operator.common;

import eval.ICalculable;
import eval.ICalculableType;
import operator.IConstants;
import operator.IOperator;
import operator.IPrecedence;
import operator.base.NullaryOperator;

import java.util.List;
import java.util.Stack;

/**
 * LazyLanguage.operator.common
 *
 * @version     02/12/2015
 * @author      Alexander Broadbent
 */
public class RightParenthesis extends NullaryOperator {

    @Override
    public String getToken() {
        return IConstants.RIGHT_PARENTHESIS;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.BRACKET;
    }

    @Override
    public int getType() {
        return ICalculableType.RIGHT_PARENTHESIS;
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        while (!operatorStack.isEmpty() && !(operatorStack.peek().getType() == ICalculableType.LEFT_PARENTHESIS))
            postfix.add(operatorStack.pop());

        if (operatorStack.peek().getType() == ICalculableType.LEFT_PARENTHESIS) {
            operatorStack.pop();

            if (operatorStack.size() > 0 && operatorStack.peek().getType() == ICalculableType.FUNCTION_OPERATOR)
                postfix.add(operatorStack.pop());
        }
        else
            throw new Error("Invalid Expression: Mis-matching brackets");
    }
}
