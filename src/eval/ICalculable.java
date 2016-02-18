package eval;

import model.Domain;
import operator.IOperator;
import parser.IncomparableTypeException;

import java.util.List;
import java.util.Stack;

/**
 * LazyLanguage.eval
 *
 * @version     01/12/2015
 * @author      Alexander Broadbent
 */
public interface ICalculable {

    Literal evaluate(Domain domain, Stack<Literal> stack) throws IncomparableTypeException;
    void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack);

    int getType();

}
