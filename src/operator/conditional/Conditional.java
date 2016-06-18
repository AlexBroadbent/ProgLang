package operator.conditional;

import com.google.common.collect.Lists;
import eval.*;
import model.Domain;
import operator.IConstants;
import operator.IOperator;
import operator.IPrecedence;
import operator.base.TernaryOperator;

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

    protected Domain domain;


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
            throws IncomparableTypeException, ExpressionException, NoValueException {
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

        this.domain = domain;

        return Domain.wrapLiteral(execute(conditional, Domain.wrapLiteral(expressionTrue), Domain.wrapLiteral(expressionFalse)));
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        while (!operatorStack.isEmpty() && operatorStack.peek().getPrecedence() < getPrecedence())
            postfix.add(operatorStack.pop());
        postfix.add(new ConditionalPlaceholder());
        operatorStack.add(this);
    }

    @Override
    @SuppressWarnings( "unchecked" )    // ClassCaseException is caught
    public Object execute(Literal arg1, Literal arg2, Literal arg3)
            throws IncomparableTypeException, NoValueException, ExpressionException {
        try {
            List<ICalculable> conTrue = (List<ICalculable>) arg2.getValue();
            List<ICalculable> conFalse = (List<ICalculable>) arg3.getValue();

            return ((Boolean) arg1.getValue()) ?
                   (new Expression(conTrue, domain.clone())).getValue() :
                   (new Expression(conFalse, domain.clone())).getValue();
        }
        catch (ClassCastException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), arg1);
        }
    }
}
