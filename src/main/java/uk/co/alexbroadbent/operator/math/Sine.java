package uk.co.alexbroadbent.operator.math;

import static uk.co.alexbroadbent.operator.IConstants.SIN;

import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.Literal;
import uk.co.alexbroadbent.operator.IPrecedence;
import uk.co.alexbroadbent.operator.base.UnaryOperator;

/**
 * x++.operator.common
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class Sine extends UnaryOperator {

    @Override
    public String getToken() {
        return SIN;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.FUNCTION;
    }

    @Override
    public Object execute(Literal arg1) throws IncomparableTypeException {
        try {
            return Math.sin(Double.parseDouble(arg1.getValue().toString()));
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), arg1);
        }
    }

}
