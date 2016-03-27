package operator.base;

import eval.Literal;
import model.Domain;
import operator.Associativity;
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
        return IPrecedence.NONE;
    }

    @Override
    public List<String> getAllowedExecutionTypes() {
        return null;
    }

    @Override
    public Literal evaluate(Domain domain, Stack<Literal> stack, boolean returnExpression)
            throws IncomparableTypeException, ExpressionException {
        throw new ExpressionException("Operator not implemented");
    }

}
