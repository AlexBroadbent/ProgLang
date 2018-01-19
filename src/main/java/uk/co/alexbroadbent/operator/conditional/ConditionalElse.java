package uk.co.alexbroadbent.operator.conditional;

import static uk.co.alexbroadbent.operator.IConstants.CONDITIONAL;

import uk.co.alexbroadbent.eval.ConditionalPlaceholder;
import uk.co.alexbroadbent.eval.ICalculable;
import uk.co.alexbroadbent.operator.IConstants;
import uk.co.alexbroadbent.operator.IOperator;
import uk.co.alexbroadbent.operator.IPrecedence;
import uk.co.alexbroadbent.operator.base.NullaryOperator;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * x++.operator.conditional
 *
 * @author Alexander Broadbent
 * @version 08/01/2016
 */
public class ConditionalElse extends NullaryOperator {

    @Override
    public String getToken() {
        return IConstants.CONDITIONAL_ELSE;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.CONDITIONAL;
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        while (!operatorStack.isEmpty() && !Objects.equals(operatorStack.peek().getToken(), CONDITIONAL))
            postfix.add(operatorStack.pop());

        postfix.add(new ConditionalPlaceholder());
    }

}
