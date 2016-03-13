package eval;

import model.Domain;
import operator.IOperator;
import parser.ExpressionException;
import parser.IncomparableTypeException;

import java.util.List;
import java.util.Stack;

/**
 * LazyLanguage.eval
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public interface ICalculable {

    Literal evaluate(Domain domain, Stack<Literal> stack) throws IncomparableTypeException, ExpressionException;

    void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack);

    int getType();

}
