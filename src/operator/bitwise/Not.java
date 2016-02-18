package operator.bitwise;

import eval.ICalculableType;
import eval.Literal;
import operator.IConstants;
import operator.IPrecedence;
import operator.base.UnaryOperator;
import parser.IncomparableTypeException;

/**
 * x++.operator.bitwise
 *
 * @author      Alexander Broadbent
 * @version     30/12/2015
 */
public class Not extends UnaryOperator {

    @Override
    public String getToken() {
        return IConstants.BITWISE_NOT;
    }

    @Override
    public int getType() {
        return ICalculableType.BITWISE_OPERATOR;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.UNARY;
    }

    @Override
    public Object execute(Literal arg1) throws IncomparableTypeException {
        try {
            return ~Byte.valueOf(arg1.getValue().toString());
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), arg1.getClass().getSimpleName());
        }
    }

}
