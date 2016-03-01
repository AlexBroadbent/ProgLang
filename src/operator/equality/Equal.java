package operator.equality;

import eval.Literal;
import operator.IConstants;
import operator.IPrecedence;
import operator.base.BinaryOperator;

/**
 * x++.operator.equality
 *
 * @author      Alexander Broadbent
 * @version     04/01/2016
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
        return arg1.getValue().equals(arg2.getValue());
    }

}
