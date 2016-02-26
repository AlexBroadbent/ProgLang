package operator.bitwise;

import eval.ICalculableType;
import eval.Literal;
import operator.IPrecedence;
import operator.base.BinaryOperator;
import parser.IncomparableTypeException;

import static operator.IConstants.RIGHT_SHIFT;

/**
 * LazyLanguage.operator.common
 *
 * @version     01/12/2015
 * @author      Alexander Broadbent
 */
public class RightShift extends BinaryOperator {

    @Override
    public String getToken() {
        return RIGHT_SHIFT;
    }

    @Override
    public int getType() {
        return ICalculableType.BITWISE_OPERATOR;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.BITSHIFT;
    }

    @Override
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException {
        try {
            return Byte.valueOf(arg1.getValue().toString()) >> Byte.valueOf(arg2.getValue().toString());
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(),
                    getIncomparableType(arg1.getClass().getSimpleName(), arg2.getClass().getSimpleName(), getAllowedExecutionTypes()));
        }
    }

}