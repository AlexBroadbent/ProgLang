package uk.co.alexbroadbent.operator.conditional;

import com.google.common.collect.Lists;
import eval.*;
import uk.co.alexbroadbent.model.Domain;
import uk.co.alexbroadbent.operator.IConstants;
import uk.co.alexbroadbent.operator.IOperator;
import uk.co.alexbroadbent.operator.IPrecedence;
import uk.co.alexbroadbent.operator.base.TernaryOperator;
import uk.co.alexbroadbent.eval.ConditionalPlaceholder;
import uk.co.alexbroadbent.eval.Expression;
import uk.co.alexbroadbent.eval.ExpressionException;
import uk.co.alexbroadbent.eval.ICalculable;
import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.Literal;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static uk.co.alexbroadbent.eval.ICalculableType.CONDITIONAL_PLACEHOLDER;

/**
 * x++.operator.conditional
 *
 * @author Alexander Broadbent
 * @version 08/01/2016
 */
public class Conditional extends TernaryOperator {

    @Override
    public String getToken() {
        return IConstants.CONDITIONAL;
    }

    @Override
    public List<String> getAllowedExecutionTypes() {
        return Lists.newArrayList(Boolean.class.getSimpleName());
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.CONDITIONAL;
    }

    @Override
    public Literal evaluate(Domain domain, Stack<Literal> stack, boolean returnExpression)
            throws IncomparableTypeException, ExpressionException {
        if (returnExpression)
            return Domain.wrapLiteral(this);

        List<ICalculable> expressionFalse = Lists.newArrayList();
        List<ICalculable> expressionTrue = Lists.newArrayList();

        while (stack.peek().getType() != CONDITIONAL_PLACEHOLDER)
            expressionFalse.add(stack.pop());
        stack.pop();
        while (stack.peek().getType() != CONDITIONAL_PLACEHOLDER)
            expressionTrue.add(stack.pop());
        stack.pop();

        Literal conditional = stack.pop();
        Collections.reverse(expressionTrue);
        Collections.reverse(expressionFalse);

        return Domain.wrapLiteral(execute(conditional, new Expression(expressionTrue, domain), new Expression(expressionFalse, domain)));
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        while (!operatorStack.isEmpty() && operatorStack.peek().getPrecedence() < getPrecedence())
            postfix.add(operatorStack.pop());
        postfix.add(new ConditionalPlaceholder());
        operatorStack.add(this);
    }

    @Override
    public Object execute(Literal arg1, Literal arg2, Literal arg3) throws IncomparableTypeException {
        try {
            return ((Boolean) arg1.getValue()) ? arg2.getValue() : arg3.getValue();
        }
        catch (ClassCastException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), arg1);
        }
    }
}
