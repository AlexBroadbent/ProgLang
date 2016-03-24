package operator.base;

import com.google.common.collect.Lists;
import eval.ICalculable;
import eval.Literal;
import model.Domain;
import operator.Associativity;
import operator.IOperator;
import parser.ExpressionException;
import parser.IncomparableTypeException;

import java.util.List;
import java.util.Stack;

/**
 * x++.operator.base
 *
 * @version     01/12/2015
 * @author      Alexander Broadbent
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
        return 99;
    }

    @Override
    public boolean isValidContext(Stack operatorStack, List infix, int position) {
        return true;
    }

    @Override
    public Literal evaluate(Domain domain, Stack<Literal> stack, boolean returnExpression) throws IncomparableTypeException, ExpressionException {
        if (returnExpression)
            return Domain.wrapLiteral(this);

        Literal arg2 = stack.pop();
        Literal arg1 = stack.pop();

        return Domain.wrapLiteral(execute(arg1, arg2));
    }

    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException, ExpressionException {
        return null;
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        if (getAssociativity() == Associativity.LEFT_TO_RIGHT) {
            // Left-associative operator
            // pop any >= precedence operators from the stack and add to output
            while (!operatorStack.isEmpty() && operatorStack.peek().getPrecedence() <= getPrecedence())
                postfix.add(operatorStack.pop());
        }
        if (getAssociativity() == Associativity.RIGHT_TO_LEFT) {
            while (!operatorStack.isEmpty() && operatorStack.peek().getPrecedence() < getPrecedence())
                postfix.add(operatorStack.pop());
        }

        operatorStack.add(this);
    }

    public List<String> getAllowedExecutionTypes() {
        return Lists.newArrayList(Double.class.getSimpleName(), Integer.class.getSimpleName());
    }

    public String getIncomparableType(String arg1Class, String arg2Class, List<String> allowedClasses) {
        return (allowedClasses.contains(arg1Class)) ? arg2Class : arg1Class;
    }

}
