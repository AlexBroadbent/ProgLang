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

import static eval.ICalculableType.VARIABLE;

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
