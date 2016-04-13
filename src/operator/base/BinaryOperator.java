package operator.base;

import com.google.common.collect.Lists;
import eval.*;
import model.Domain;
import operator.Associativity;
import operator.IOperator;
import operator.IPrecedence;

import java.util.List;
import java.util.Stack;

import static eval.ICalculableType.VARIABLE;

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

    public String getIncomparableType(String arg1Class, String arg2Class, List<String> allowedClasses) {
        return (allowedClasses.contains(arg1Class)) ? arg2Class : arg1Class;
    }

}
