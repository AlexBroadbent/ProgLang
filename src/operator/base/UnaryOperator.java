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
        return 99;
    }

    @Override
    public Literal evaluate(Domain domain, Stack<Literal> stack, boolean funcDec) throws IncomparableTypeException {
        if (funcDec)
            return Domain.wrapLiteral(this);
        return Domain.wrapLiteral(execute(stack.pop()));
    }

    public Object execute(Literal arg1) throws IncomparableTypeException {
        return arg1;
    }

    @Override
    public boolean isValidContext(Stack operatorStack, List infix, int position) {
        return true;
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        if (getAssociativity() == Associativity.LEFT_TO_RIGHT) {
            // pre-fix unary operator. Add to operator stack
            operatorStack.push(this);
        }
        if (getAssociativity() == Associativity.RIGHT_TO_LEFT) {
            // post-fix unary operator. Append to postfix output
            postfix.add(this);
        }
    }

    @Override
    public List<String> getAllowedExecutionTypes() {
        return Lists.newArrayList(Double.class.getSimpleName(), Integer.class.getSimpleName());
    }

}
