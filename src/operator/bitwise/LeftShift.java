package operator.bitwise;

import com.google.common.collect.Lists;
import eval.IncomparableTypeException;
import eval.Literal;
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
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException {
        try {
            return Byte.valueOf(arg1.getValue().toString()) << Byte.valueOf(arg2.getValue().toString());
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(),
                    getIncomparableType(arg1.getClass().getSimpleName(), arg2.getClass().getSimpleName(), getAllowedExecutionTypes()));
        }
    }

}
