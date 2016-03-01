package operator.math;

import eval.Literal;
import operator.IPrecedence;
import operator.base.UnaryOperator;
import parser.IncomparableTypeException;

import static operator.IConstants.ATAN;

/**
 * LazyLanguage.operator.common
 *
 * @author      Alexander Broadbent
 * @version     01/12/2015
 */
public class ATangent extends UnaryOperator {

    @Override
    public String getToken() {
        return ATAN;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.FUNCTION;
    }

    @Override
    public Object execute(Literal arg1) throws IncomparableTypeException {
        try {
            return Math.atan(Double.parseDouble(arg1.getValue().toString()));
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), arg1.getClass().getSimpleName());
        }
    }

}
