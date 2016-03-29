package operator.base;

import eval.ICalculable;
import eval.Literal;
import model.Domain;
import operator.Associativity;
import operator.IOperator;
import operator.IPrecedence;
import parser.ExpressionException;
import parser.IncomparableTypeException;

import java.util.List;
import java.util.Stack;

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
    public Literal evaluate(Domain domain, Stack<Literal> stack, boolean returnExpression)
            throws IncomparableTypeException, ExpressionException {
        if (returnExpression)
            return Domain.wrapLiteral(this);

        Literal arg3 = stack.pop();
        Literal arg2 = stack.pop();
        Literal arg1 = stack.pop();

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
            throws IncomparableTypeException, ExpressionException {
        throw new ExpressionException("Operator not implemented");
    }

    public String getIncomparableType(String arg1Class, String arg2Class, String arg3Class, List<String> allowedClasses) {
        return (!allowedClasses.contains(arg1Class)) ? arg1Class : (!allowedClasses.contains(arg2Class) ? arg2Class : arg3Class);
    }

}
