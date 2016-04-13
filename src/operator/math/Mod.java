package operator.math;

import eval.IncomparableTypeException;
import eval.Literal;
import operator.IPrecedence;
import operator.base.BinaryOperator;

import static operator.IConstants.MOD;

/**
 * x++.operator.common
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class Mod extends BinaryOperator {

    @Override
    public String getToken() {
        return MOD;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.GEOMETRIC;
    }

    @Override
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException {
        try {
            return Double.valueOf(arg1.getValue().toString()) % Double.valueOf(arg2.getValue().toString());
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(),
                    getIncomparableType(arg1.getClass().getSimpleName(), arg2.getClass().getSimpleName(), getAllowedExecutionTypes()));
        }
    }

}
