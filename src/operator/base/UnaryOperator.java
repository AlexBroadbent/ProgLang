package operator.base;

import com.google.common.collect.Lists;
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
public abstract class UnaryOperator extends Operator {

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public int getNumOperands() {
        return 1;
    }

    @Override
    public Associativity getAssociativity() {
        return Associativity.LEFT_TO_RIGHT;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.NONE;
    }

    @Override
    public List<String> getAllowedExecutionTypes() {
        return Lists.newArrayList(Double.class.getSimpleName(), Integer.class.getSimpleName());
    }

    @Override
    public Literal evaluate(Domain domain, Stack<Literal> stack, boolean returnExpression)
            throws IncomparableTypeException, ExpressionException {
        if (returnExpression)
            return Domain.wrapLiteral(this);
        return Domain.wrapLiteral(execute(stack.pop()));
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        if (getAssociativity() == Associativity.LEFT_TO_RIGHT)
            operatorStack.push(this);   // pre-fix unary operator, add to operator stack
        if (getAssociativity() == Associativity.RIGHT_TO_LEFT)
            postfix.add(this);          // post-fix unary operator, add to postfix output
    }

    public Object execute(Literal arg1) throws IncomparableTypeException, ExpressionException {
        throw new ExpressionException("Operator not implemented");
    }

}
