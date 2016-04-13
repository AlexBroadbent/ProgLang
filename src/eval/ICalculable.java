package eval;

import model.Domain;
import operator.IOperator;

import java.util.List;
import java.util.Stack;

/**
 * x++.eval
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public interface ICalculable {

    Literal evaluate(Domain domain, Stack<Literal> stack, boolean returnExpression)
            throws IncomparableTypeException, ExpressionException;

    void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack);

    int getType();

}
