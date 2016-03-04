package operator.list;

import eval.ICalculable;
import eval.ICalculableType;
import operator.IConstants;
import operator.IOperator;
import operator.IPrecedence;
import operator.base.NullaryOperator;
import operator.function.ArgSeparator;

import java.util.List;
import java.util.Stack;

/**
 * ProgLang.operator.list
 *
 * @author      Alexander Broadbent
 * @version     27/02/2016
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
        postfix.add(new ArgSeparator());
        operatorStack.push(this);
    }

}
