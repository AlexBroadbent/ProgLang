package uk.co.alexbroadbent.operator.bitwise;

import uk.co.alexbroadbent.eval.ExpressionException;
import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.Literal;
import uk.co.alexbroadbent.operator.IConstants;
import uk.co.alexbroadbent.operator.IPrecedence;
import uk.co.alexbroadbent.operator.base.UnaryOperator;

/**
 * x++.operator.bitwise
 *
 * @author Alexander Broadbent
 * @version 30/12/2015
 */
public class Not extends UnaryOperator {

    @Override
    public String getToken() {
        return IConstants.BITWISE_NOT;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.UNARY;
    }

    @Override
    public Object execute(Literal arg1) throws IncomparableTypeException, ExpressionException {
        try {
            // Validate that input can be parsed into a Byte
            Double d1 = Double.parseDouble(arg1.getValue().toString());
            if (d1 < Byte.MIN_VALUE)
                throw new ExpressionException(String.format(MSG_BYTE_SIZE, d1, MSG_SMALL));
            if (d1 > Byte.MAX_VALUE)
                throw new ExpressionException(String.format(MSG_BYTE_SIZE, d1, MSG_LARGE));

            return ~Byte.valueOf(arg1.getValue().toString());
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), arg1);
        }
    }

}
