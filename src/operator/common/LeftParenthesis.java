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
public class LeftParenthesis extends NullaryOperator {

    @Override
    public String getToken() {
        return IConstants.LEFT_PARENTHESIS;
    }

    @Override
    public int getType() {
        return ICalculableType.LEFT_PARENTHESIS;
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        operatorStack.push(this);
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.BRACKET;
    }

}
