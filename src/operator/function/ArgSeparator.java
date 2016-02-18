package operator.function;

import eval.ICalculable;
import eval.ICalculableType;
import operator.IConstants;
import operator.IOperator;
import operator.IPrecedence;
import operator.base.NullaryOperator;

import java.util.List;
import java.util.Stack;

/**
 * LazyLanguage.operator.function
 *
 * @version     02/12/2015
 * @author      Alexander Broadbent
 */
public class ArgSeparator extends NullaryOperator {

    @Override
    public String getToken() {
        return IConstants.ARG_SEPARATOR;
    }

    @Override
    public int getType() {
        return ICalculableType.FUNCTION_OPERATOR;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.ARG_SEPARATOR;
    }

    @Override
    public boolean isValidContext(Stack<IOperator> operatorStack, List<ICalculable> infix, int position) {
        for (int i=operatorStack.size(); i>=0; --i)
            if (operatorStack.get(i) instanceof Function)
                return true;

        return false;
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {

        while (!(operatorStack.peek().getType() == ICalculableType.LEFT_PARENTHESIS))
            postfix.add(operatorStack.pop());
    }
}
