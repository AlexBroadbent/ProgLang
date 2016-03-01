package operator.function;

import eval.ICalculable;
import eval.ICalculableType;
import eval.Literal;
import model.Domain;
import operator.IConstants;
import operator.IOperator;
import operator.IPrecedence;
import operator.base.NullaryOperator;
import parser.IncomparableTypeException;

import java.util.List;
import java.util.Stack;

/**
 * LazyLanguage.operator.function
 *
 * @author Alexander Broadbent
 * @version 02/12/2015
 */
public class ArgSeparator extends NullaryOperator {

    @Override
    public String getToken() {
        return IConstants.ARG_SEPARATOR;
    }

    @Override
    public int getType() {
        return ICalculableType.ARG_SEPARATOR;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.ARG_SEPARATOR;
    }

    @Override
    public boolean isValidContext(Stack<IOperator> operatorStack, List<ICalculable> infix, int position) {
        for (int i = operatorStack.size(); i >= 0; --i)
            if (operatorStack.get(i) instanceof Function)
                return true;
        return false;
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        while (!(operatorStack.peek().getType() == ICalculableType.LEFT_PARENTHESIS))
            postfix.add(operatorStack.pop());
    }

    @Override
    public Literal evaluate(Domain domain, Stack<Literal> stack) throws IncomparableTypeException {
        return null;
    }

}
