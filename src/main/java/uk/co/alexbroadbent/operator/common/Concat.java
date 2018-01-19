package uk.co.alexbroadbent.operator.common;

import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.Literal;
import uk.co.alexbroadbent.operator.IPrecedence;
import uk.co.alexbroadbent.operator.base.BinaryOperator;

import static uk.co.alexbroadbent.operator.IConstants.CONCAT;

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
