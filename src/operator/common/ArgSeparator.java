package operator.common;

import eval.ICalculable;
import eval.ICalculableType;
import eval.IncomparableTypeException;
import eval.Literal;
import model.Domain;
import operator.IConstants;
import operator.IOperator;
import operator.IPrecedence;
import operator.base.NullaryOperator;

import java.util.List;
import java.util.Stack;

import static eval.ICalculableType.LEFT_PARENTHESIS;
import static eval.ICalculableType.LIST_START;

/**
 * x++.operator.function
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
    public int getPrecedence() {
        return IPrecedence.ARG_SEPARATOR;
    }

    @Override
    public Literal evaluate(Domain domain, Stack<Literal> stack, boolean returnExpression)
            throws IncomparableTypeException {
        return Domain.wrapLiteral(this);
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        while (!operatorStack.isEmpty() && !(operatorStack.peek().getType() == LEFT_PARENTHESIS || operatorStack.peek().getType() == LIST_START))
            postfix.add(operatorStack.pop());
    }

    @Override
    public int getType() {
        return ICalculableType.ARG_SEPARATOR;
    }

}
