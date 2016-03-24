package operator.base;

import com.google.common.collect.Lists;
import eval.ICalculable;
import eval.Literal;
import model.Domain;
import operator.Associativity;
import operator.IOperator;
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
        return 99;
    }

    public List<String> getAllowedExecutionTypes() {
        return Lists.newArrayList(Double.class.getSimpleName(), Integer.class.getSimpleName());
    }

    @Override
    public boolean isValidContext(Stack operatorStack, List infix, int position) {
        return true;
    }

    public Object execute(Literal arg1, Literal arg2, Literal arg3) throws IncomparableTypeException {
        return null;
    }

    @Override
    public Literal evaluate(Domain domain, Stack<Literal> stack, boolean returnExpression)
            throws IncomparableTypeException {
        if (returnExpression)
            return Domain.wrapLiteral(this);

        Literal ifFalse = stack.pop();
        Literal ifTrue = stack.pop();
        Literal cond = stack.pop();

        return Domain.wrapLiteral(execute(cond, ifTrue, ifFalse));
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

    public String getIncomparableType(String arg1Class, String arg2Class, List<String> allowedClasses) {
        return (allowedClasses.contains(arg1Class)) ? arg2Class : arg1Class;
    }

}
