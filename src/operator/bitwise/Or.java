package operator.bitwise;

import eval.ExpressionException;
import eval.IncomparableTypeException;
import eval.Literal;
import operator.IPrecedence;
import operator.base.BinaryOperator;

import static operator.IConstants.BITWISE_OR;

/**
 * x++.operator.common
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class Or extends BinaryOperator {

    @Override
    public String getToken() {
        return BITWISE_OR;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.BITWISE_OR;
    }

    @Override
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException, ExpressionException {
        try {
            // Validate that inputs can be parsed into a Byte
            Double d1 = Double.parseDouble(arg1.getValue().toString());
            Double d2 = Double.parseDouble(arg2.getValue().toString());
            if (d1 < Byte.MIN_VALUE)
                throw new ExpressionException(String.format(MSG_BYTE_SIZE, d1, MSG_SMALL));
            if (d1 > Byte.MAX_VALUE)
                throw new ExpressionException(String.format(MSG_BYTE_SIZE, d1, MSG_LARGE));
            if (d2 < Byte.MIN_VALUE)
                throw new ExpressionException(String.format(MSG_BYTE_SIZE, d2, MSG_SMALL));
            if (d2 > Byte.MAX_VALUE)
                throw new ExpressionException(String.format(MSG_BYTE_SIZE, d2, MSG_LARGE));

            return Byte.valueOf(arg1.getValue().toString()) | Byte.valueOf(arg2.getValue().toString());
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(),
                    getIncomparableType(arg1.getClass().getSimpleName(), arg2.getClass().getSimpleName(), getAllowedExecutionTypes()));
        }
    }
}
