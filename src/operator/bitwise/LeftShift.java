package operator.bitwise;

import com.google.common.collect.Lists;
import eval.ExpressionException;
import eval.IncomparableTypeException;
import eval.Literal;
import eval.NoValueException;
import operator.IPrecedence;
import operator.base.BinaryOperator;

import java.util.List;

import static operator.IConstants.LEFT_SHIFT;

/**
 * x++.operator.common
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class LeftShift extends BinaryOperator {

    @Override
    public String getToken() {
        return LEFT_SHIFT;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.BIT_SHIFT;
    }

    @Override
    public List<String> getAllowedExecutionTypes() {
        return Lists.newArrayList(Double.class.getSimpleName(), Integer.class.getSimpleName());
    }

    @Override
    public Object execute(Literal arg1, Literal arg2)
            throws IncomparableTypeException, ExpressionException, NoValueException {
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

            return Byte.valueOf(arg1.getValue().toString()) << Byte.valueOf(arg2.getValue().toString());
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(),
                    getIncomparableObject(arg1, arg2, getAllowedExecutionTypes()));
        }
    }

}
