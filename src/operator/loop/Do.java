package operator.loop;

import eval.ExpressionException;
import eval.ICalculable;
import eval.IncomparableTypeException;
import eval.Literal;
import operator.IConstants;
import operator.IOperator;
import operator.IPrecedence;
import operator.base.UnaryOperator;

import java.util.List;
import java.util.Stack;

/**
 * ProgLang.operator.loop
 *
 * @author Alexander Broadbent
 * @version 27/03/2016
 */
public class Do extends UnaryOperator {

    @Override
    public String getToken() {
        return IConstants.DO;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.LOOP;
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        postfix.add(this);
    }

    @Override
    public Object execute(Literal arg1) throws IncomparableTypeException, ExpressionException {
        return null;
    }

}
