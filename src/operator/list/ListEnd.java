package operator.list;

import com.google.common.collect.Lists;
import eval.ICalculable;
import eval.Literal;
import operator.IConstants;
import operator.IOperator;
import operator.IPrecedence;
import operator.function.Function;
import parser.ExpressionException;
import parser.IncomparableTypeException;

import java.util.List;
import java.util.Stack;

import static eval.ICalculableType.LIST_START;

/**
 * x++.operator.list
 *
 * @author Alexander Broadbent
 * @version 27/02/2016
 */
public class ListEnd extends Function {

    @Override
    public String getToken() {
        return IConstants.LIST_END;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.LIST;
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        operatorStack.push(this);

        // remove ListStart from operator stack, allow for logic within by using for loop
        for (IOperator operator : operatorStack)
            if (operator.getType() == LIST_START)
                operatorStack.remove(operator);
    }

    @Override
    public Object execute(List<Literal> args) throws IncomparableTypeException, ExpressionException {
        return Lists.newLinkedList(args);
    }

    @Override
    public String toString() {
        return ListEnd.class.getSimpleName();
    }

}
