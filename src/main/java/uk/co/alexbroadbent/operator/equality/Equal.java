package uk.co.alexbroadbent.operator.equality;

import uk.co.alexbroadbent.eval.Literal;
import uk.co.alexbroadbent.operator.IConstants;
import uk.co.alexbroadbent.operator.IPrecedence;
import uk.co.alexbroadbent.operator.base.BinaryOperator;

import java.util.Objects;

/**
 * x++.operator.equality
 *
 * @author Alexander Broadbent
 * @version 04/01/2016
 */
public class Equal extends BinaryOperator {

    @Override
    public String getToken() {
        return IConstants.EQUALITY;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.BOOLEAN_COMPARATOR;
    }

    @Override
    public Object execute(Literal arg1, Literal arg2) {
        return Objects.equals(arg1.getValue(), arg2.getValue());
    }

}
