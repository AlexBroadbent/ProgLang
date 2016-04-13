package operator.common;

import eval.IncomparableTypeException;
import eval.Literal;
import operator.IPrecedence;
import operator.base.BinaryOperator;

import static operator.IConstants.CONCAT;

/**
 * x++.operator.common
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class Concat extends BinaryOperator {

    @Override
    public String getToken() {
        return CONCAT;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.ARITHMETIC;
    }

    @Override
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException {
        return arg1.getValue().toString() + arg2.getValue().toString();
    }

}
