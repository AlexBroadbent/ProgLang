package operator.common;

import eval.ICalculableType;
import eval.Literal;
import operator.IPrecedence;
import operator.base.BinaryOperator;
import parser.IncomparableTypeException;

import static operator.IConstants.PLUS;

/**
 * LazyLanguage.operator.common
 *
 * @author      Alexander Broadbent
 * @version     01/12/2015
 */
public class Add extends BinaryOperator {

    @Override
    public String getToken() {
        return PLUS;
    }

    @Override
    public int getType() {
        return ICalculableType.COMMON_OPERATOR;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.ARITHMETIC;
    }

    @Override
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException {
        try {
            return Double.valueOf(arg1.getValue().toString()) + Double.valueOf(arg2.getValue().toString());
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(),
                    getIncomparableType(arg1.getClass().getSimpleName(), arg2.getClass().getSimpleName(), getAllowedExecutionTypes()));
        }
    }

}
