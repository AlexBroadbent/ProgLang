package operator.list;

import eval.FunctionPlaceholder;
import eval.ICalculable;
import eval.ICalculableType;
import operator.IConstants;
import operator.IOperator;
import operator.IPrecedence;
import operator.base.NullaryOperator;

import java.util.List;
import java.util.Stack;

/**
 * x++.operator.list
 *
 * @author Alexander Broadbent
 * @version 27/02/2016
 */
public class ListStart extends NullaryOperator {

    @Override
    public String getToken() {
        return IConstants.LIST_START;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.LIST;
    }

    @Override
    public int getType() {
        return ICalculableType.LIST_START;
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        postfix.add(new FunctionPlaceholder());
        operatorStack.push(this);
    }

}
