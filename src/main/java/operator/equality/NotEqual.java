package operator.equality;

import eval.Literal;
import operator.IConstants;
import operator.IPrecedence;
import operator.base.BinaryOperator;

import java.util.Objects;

/**
 * x++.operator.equality
 *
 * @author Alexander Broadbent
 * @version 04/01/2016
 */
public class NotEqual extends BinaryOperator {

    @Override
    public String getToken() {
        return IConstants.INEQUALITY;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.BOOLEAN_COMPARATOR;
    }

    @Override
    public Object execute(Literal arg1, Literal arg2) {
        return !Objects.equals(arg1.getValue(), arg2.getValue());
    }

}
