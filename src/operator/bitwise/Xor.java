package operator.bitwise;

import eval.Literal;
import operator.IPrecedence;
import operator.base.BinaryOperator;
import parser.IncomparableTypeException;

import static operator.IConstants.BITWISE_XOR;

/**
 * x++.operator.common
 *
 * @version     01/12/2015
 * @author      Alexander Broadbent
 */
public class Xor extends BinaryOperator {

    @Override
    public String getToken() {
        return BITWISE_XOR;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.BITWISE_XOR;
    }

    @Override
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException {
        try {
            return Byte.valueOf(arg1.getValue().toString()) ^ Byte.valueOf(arg2.getValue().toString());
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(),
                    getIncomparableType(arg1.getClass().getSimpleName(), arg2.getClass().getSimpleName(), getAllowedExecutionTypes()));
        }
    }
}
