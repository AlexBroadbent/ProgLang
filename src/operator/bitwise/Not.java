package operator.bitwise;

import eval.ExpressionException;
import eval.IncomparableTypeException;
import eval.Literal;
import operator.IConstants;
import operator.IPrecedence;
import operator.base.UnaryOperator;

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
            throw new IncomparableTypeException(getAllowedExecutionTypes(), arg1.getClass().getSimpleName());
        }
    }

}
