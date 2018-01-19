package uk.co.alexbroadbent.operator.loop;

import uk.co.alexbroadbent.eval.ExpressionException;
import uk.co.alexbroadbent.eval.ICalculable;
import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.Literal;
import uk.co.alexbroadbent.operator.IConstants;
import uk.co.alexbroadbent.operator.IOperator;
import uk.co.alexbroadbent.operator.IPrecedence;
import uk.co.alexbroadbent.operator.base.UnaryOperator;

import java.util.List;
import java.util.Stack;

/**
 * x++.operator.loop
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
