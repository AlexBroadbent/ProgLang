package uk.co.alexbroadbent.operator.base;

import com.google.common.collect.Lists;
import eval.*;
import uk.co.alexbroadbent.model.Domain;
import uk.co.alexbroadbent.operator.Associativity;
import uk.co.alexbroadbent.operator.IOperator;
import uk.co.alexbroadbent.operator.IPrecedence;
import uk.co.alexbroadbent.eval.ExpressionException;
import uk.co.alexbroadbent.eval.ICalculable;
import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.Literal;
import uk.co.alexbroadbent.eval.Variable;

import java.util.List;
import java.util.Stack;

import static uk.co.alexbroadbent.eval.ICalculableType.VARIABLE;

/**
 * x++.operator.base
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public abstract class BinaryOperator extends Operator {

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public int getNumOperands() {
        return 2;
    }

    @Override
    public Associativity getAssociativity() {
        return Associativity.LEFT_TO_RIGHT;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.NONE;
    }

    public List<String> getAllowedExecutionTypes() {
        return Lists.newArrayList(Double.class.getSimpleName(), Integer.class.getSimpleName());
    }

    @Override
    public Literal evaluate(Domain domain, Stack<Literal> stack, boolean returnExpression)
            throws IncomparableTypeException, ExpressionException {
        if (returnExpression)
            return Domain.wrapLiteral(this);

        Literal arg2 = stack.pop();
        Literal arg1 = stack.pop();

        // Assert that the operation is not attempting to be executed on a variable that does not have a value
        if (arg1.getType() == VARIABLE)
            if (!((Variable) arg1).isValueSet())
                throw new ExpressionException(String.format(MSG_VALUE_NOT_SET, ((Variable) arg1).getName()));
        if (arg2.getType() == VARIABLE)
            if (!((Variable) arg2).isValueSet())
                throw new ExpressionException(String.format(MSG_VALUE_NOT_SET, ((Variable) arg2).getName()));

        return Domain.wrapLiteral(execute(arg1, arg2));
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        if (getAssociativity() == Associativity.LEFT_TO_RIGHT)
            while (!operatorStack.isEmpty() && operatorStack.peek().getPrecedence() <= getPrecedence())
                postfix.add(operatorStack.pop());
        if (getAssociativity() == Associativity.RIGHT_TO_LEFT)
            while (!operatorStack.isEmpty() && operatorStack.peek().getPrecedence() < getPrecedence())
                postfix.add(operatorStack.pop());

        operatorStack.add(this);
    }

    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException, ExpressionException {
        throw new ExpressionException("Operator not implemented");
    }

    public ICalculable getIncomparableObject(ICalculable arg1, ICalculable arg2, List<String> allowedClasses) {
        String a1Class = (arg1.getType() == VARIABLE) ? ((Variable) arg1).getValue().getClass().getSimpleName() : arg1.getClass().getSimpleName();
        return (allowedClasses.contains(a1Class)) ? arg2 : arg1;
    }

}
