package uk.co.alexbroadbent.operator.math;

import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.Literal;
import uk.co.alexbroadbent.operator.IConstants;
import uk.co.alexbroadbent.operator.IPrecedence;
import uk.co.alexbroadbent.operator.base.UnaryOperator;

/**
 * x++.operator.math
 *
 * @author Alexander Broadbent
 * @version 31/12/2015
 */
public class SquareRoot extends UnaryOperator {

    @Override
    public String getToken() {
        return IConstants.SQUARE_ROOT;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.FUNCTION;
    }

    @Override
    public Object execute(Literal arg1) throws IncomparableTypeException {
        try {
            return Math.sqrt(Double.parseDouble(arg1.getValue().toString()));
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), arg1);
        }
    }

}
