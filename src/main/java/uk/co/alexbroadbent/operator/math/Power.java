package uk.co.alexbroadbent.operator.math;

import static uk.co.alexbroadbent.operator.IConstants.POWER;

import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.Literal;
import uk.co.alexbroadbent.operator.IPrecedence;
import uk.co.alexbroadbent.operator.base.BinaryOperator;

/**
 * x++.operator.common
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class Power extends BinaryOperator {

    @Override
    public String getToken() {
        return POWER;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.GEOMETRIC;
    }

    @Override
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException {
        try {
            return Math.pow(Double.valueOf(arg1.getValue().toString()), Double.valueOf(arg2.getValue().toString()));
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(),
                    getIncomparableObject(arg1, arg2, getAllowedExecutionTypes()));
        }
    }
}
