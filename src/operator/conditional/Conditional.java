package operator.conditional;

import com.google.common.collect.Lists;
import eval.ConditionalPlaceholder;
import eval.Expression;
import eval.ICalculable;
import eval.Literal;
import model.Domain;
import operator.IConstants;
import operator.IOperator;
import operator.IPrecedence;
import operator.base.TernaryOperator;
import parser.ExpressionException;
import parser.IncomparableTypeException;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static eval.ICalculableType.CONDITIONAL_PLACEHOLDER;

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
            return ((Boolean) arg1.getValue())
                   ? ((arg2 instanceof Expression) ? arg2.getValue() : arg2)
                   : ((arg3 instanceof Expression) ? arg3.getValue() : arg3);
        }
        catch (ClassCastException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), arg1.getValue().getClass().getSimpleName());
        }
    }
}
