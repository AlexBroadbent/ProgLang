package operator.math;

import eval.Literal;
import operator.IPrecedence;
import operator.base.BinaryOperator;
import parser.IncomparableTypeException;

import static operator.IConstants.POWER;

/**
 * LazyLanguage.operator.common
 *
 * @version     01/12/2015
 * @author      Alexander Broadbent
 */
public class Power extends BinaryOperator {

    @Override
    public String getToken() {
        return POWER;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.GEOMETRIC;
    }

    @Override
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException {
        try {
            return Math.pow(Double.valueOf(arg1.getValue().toString()), Double.valueOf(arg2.getValue().toString()));
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(),
                    getIncomparableType(arg1.getClass().getSimpleName(), arg2.getClass().getSimpleName(), getAllowedExecutionTypes()));
        }
    }
}
