package uk.co.alexbroadbent.operator.common;

import uk.co.alexbroadbent.eval.ICalculable;
import uk.co.alexbroadbent.eval.ICalculableType;
import uk.co.alexbroadbent.operator.IConstants;
import uk.co.alexbroadbent.operator.IOperator;
import uk.co.alexbroadbent.operator.IPrecedence;
import uk.co.alexbroadbent.operator.base.NullaryOperator;

import java.util.List;
import java.util.Stack;

import static uk.co.alexbroadbent.eval.ICalculableType.FUNCTION;

/**
 * x++.operator.common
 *
 * @author Alexander Broadbent
 * @version 02/12/2015
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
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        while (!operatorStack.isEmpty() && !(operatorStack.peek().getType() == ICalculableType.LEFT_PARENTHESIS))
            postfix.add(operatorStack.pop());

        if (operatorStack.peek().getType() == ICalculableType.LEFT_PARENTHESIS) {
            operatorStack.pop();

            if (operatorStack.size() > 0 && operatorStack.peek().getType() == FUNCTION)
                postfix.add(operatorStack.pop());
        }
        else
            throw new Error("Invalid Expression: Mis-matching brackets");
    }

    @Override
    public int getType() {
        return ICalculableType.RIGHT_PARENTHESIS;
    }
}
