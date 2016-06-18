package operator.base;

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
public abstract class TernaryOperator extends Operator {

    @Override
    public int getNumOperands() {
        return 3;
    }

    @Override
    public Associativity getAssociativity() {
        return Associativity.RIGHT_TO_LEFT;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.NONE;
    }

    @Override
    public Literal evaluate(Domain domain, Stack<Literal> stack)
            throws IncomparableTypeException, ExpressionException, NoValueException {
        return evaluate(domain, stack, false);
    }

    @Override
    public Literal evaluate(Domain domain, Stack<Literal> stack, boolean returnExpression)
            throws IncomparableTypeException, ExpressionException, NoValueException {
        if (returnExpression)
            return Domain.wrapLiteral(this);

        Literal arg3 = stack.pop();
        Literal arg2 = stack.pop();
        Literal arg1 = stack.pop();


        // Assert that the operation is not attempting to be executed on a variable that does not have a value
        if (arg1.getType() == VARIABLE)
            if (!((Variable) arg1).isValueSet())
                throw new ExpressionException(String.format(MSG_VALUE_NOT_SET, ((Variable) arg1).getName()));
        if (arg2.getType() == VARIABLE)
            if (!((Variable) arg2).isValueSet())
                throw new ExpressionException(String.format(MSG_VALUE_NOT_SET, ((Variable) arg2).getName()));
        if (arg3.getType() == VARIABLE)
            if (!((Variable) arg3).isValueSet())
                throw new ExpressionException(String.format(MSG_VALUE_NOT_SET, ((Variable) arg3).getName()));

        return Domain.wrapLiteral(execute(arg1, arg2, arg3));
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

    public Object execute(Literal arg1, Literal arg2, Literal arg3)
            throws IncomparableTypeException, ExpressionException, NoValueException {
        throw new ExpressionException("Operator not implemented");
    }

}
