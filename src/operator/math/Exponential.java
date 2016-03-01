package operator.math;

import eval.Literal;
import operator.IPrecedence;
import operator.base.UnaryOperator;
import parser.IncomparableTypeException;

import static operator.IConstants.EXPONENTIAL;

/**
 * LazyLanguage.operator.common
 *
 * @version     01/12/2015
 * @author      Alexander Broadbent
 */
public class Exponential extends UnaryOperator {

    @Override
    public String getToken() {
        return EXPONENTIAL;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.FUNCTION;
    }

    @Override
    public Object execute(Literal arg1) throws IncomparableTypeException {
        try {
            return Math.exp(Double.valueOf(arg1.getValue().toString()));
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), arg1.getClass().getSimpleName());
        }
    }

}
