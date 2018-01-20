package uk.co.alexbroadbent.operator.list;

import uk.co.alexbroadbent.eval.ICalculable;
import uk.co.alexbroadbent.eval.ICalculableType;
import uk.co.alexbroadbent.operator.IConstants;
import uk.co.alexbroadbent.operator.IOperator;
import uk.co.alexbroadbent.operator.IPrecedence;
import uk.co.alexbroadbent.operator.base.NullaryOperator;

import java.util.List;
import java.util.Stack;

/**
 * x++.operator.list
 *
 * @author Alexander Broadbent
 * @version 26/03/2016
 */
public class ArrayAccessStart extends NullaryOperator {

    @Override
    public String getToken() {
        return IConstants.ARRAY_ACCESS_START;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.ARRAY_ACCESS;
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {

    }

    @Override
    public int getType() {
        return ICalculableType.OPERATOR;
    }

}
