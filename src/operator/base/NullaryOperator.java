package operator.base;

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
 * @version     01/12/2015
 * @author      Alexander Broadbent
 */
public abstract class NullaryOperator extends Operator {

    @Override
    public abstract String getToken();

    @Override
    public int getNumOperands() {
        return 0;
    }

    @Override
    public Associativity getAssociativity() {
        return Associativity.NONE;
    }

    @Override
    public int getPrecedence() {
        return 99;
    }

    @Override
    public Literal evaluate(Domain domain, Stack<Literal> stack, boolean funcDec) throws IncomparableTypeException {
        throw new Error("Operator not implemented");
    }

    @Override
    public boolean isValidContext(Stack<IOperator> operatorStack, List<ICalculable> infix, int position) {
        return true;
    }

    @Override
    public List<String> getAllowedExecutionTypes() {
        return null;
    }

}
