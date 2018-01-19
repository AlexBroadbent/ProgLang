package uk.co.alexbroadbent.operator.common;

import static uk.co.alexbroadbent.eval.ICalculableType.LEFT_PARENTHESIS;
import static uk.co.alexbroadbent.eval.ICalculableType.LIST_START;

import uk.co.alexbroadbent.eval.ICalculable;
import uk.co.alexbroadbent.eval.ICalculableType;
import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.Literal;
import uk.co.alexbroadbent.model.Domain;
import uk.co.alexbroadbent.operator.IConstants;
import uk.co.alexbroadbent.operator.IOperator;
import uk.co.alexbroadbent.operator.IPrecedence;
import uk.co.alexbroadbent.operator.base.NullaryOperator;

import java.util.List;
import java.util.Stack;

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
