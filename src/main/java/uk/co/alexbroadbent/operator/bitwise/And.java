package uk.co.alexbroadbent.operator.bitwise;

import com.google.common.collect.Lists;
import uk.co.alexbroadbent.eval.ExpressionException;
import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.Literal;
import uk.co.alexbroadbent.operator.IPrecedence;
import uk.co.alexbroadbent.operator.base.BinaryOperator;

import static uk.co.alexbroadbent.operator.IConstants.BITWISE_AND;

/**
 * x++.operator.common
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class And extends BinaryOperator {

    @Override
    public String getToken() {
        return BITWISE_AND;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.BITWISE_AND;
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

            return Byte.valueOf(arg1.getValue().toString()) & Byte.valueOf(arg2.getValue().toString());
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(Lists.newArrayList(Byte.class.getSimpleName()),
                    getIncomparableObject(arg1, arg2, getAllowedExecutionTypes()));
        }
    }

}
