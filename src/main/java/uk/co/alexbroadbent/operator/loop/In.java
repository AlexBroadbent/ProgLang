package uk.co.alexbroadbent.operator.loop;

import static uk.co.alexbroadbent.eval.ICalculableType.VARIABLE;

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
public class In extends UnaryOperator {

    @Override
    public String getToken() {
        return IConstants.IN;
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
        // Validate that the preceding literal is a variable
        if (arg1.getType() == VARIABLE)
            return arg1;

        throw new ExpressionException("Expected a variable between FOR and IN, instead found " + arg1.getClass().getSimpleName());
    }

}
