package uk.co.alexbroadbent.operator.common;

import uk.co.alexbroadbent.eval.ICalculable;
import uk.co.alexbroadbent.eval.ICalculableType;
import uk.co.alexbroadbent.operator.IConstants;
import uk.co.alexbroadbent.operator.IOperator;
import uk.co.alexbroadbent.operator.IPrecedence;
import uk.co.alexbroadbent.operator.base.NullaryOperator;

import java.util.List;
import java.util.Stack;

/**
 * x++.operator.common
 *
 * @author Alexander Broadbent
 * @version 02/12/2015
 */
public class LeftParenthesis extends NullaryOperator {

    @Override
    public String getToken() {
        return IConstants.LEFT_PARENTHESIS;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.BRACKET;
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        operatorStack.push(this);
    }

    @Override
    public int getType() {
        return ICalculableType.LEFT_PARENTHESIS;
    }

}
