package operator.bitwise;

import com.google.common.collect.Lists;
import eval.Literal;
import operator.IPrecedence;
import operator.base.BinaryOperator;
import parser.IncomparableTypeException;

import java.util.List;

import static operator.IConstants.BITWISE_AND;

/**
 * x++.operator.common
 *
 * @version     01/12/2015
 * @author      Alexander Broadbent
 */
public class And extends BinaryOperator {

    @Override
    public String getToken() {
        return BITWISE_AND;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.BITWISE_AND;
    }

    @Override
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException {
        try {
            return Byte.valueOf(arg1.getValue().toString()) & Byte.valueOf(arg2.getValue().toString());
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(Byte.class.getSimpleName(),
                    getIncomparableType(arg1.getClass().getSimpleName(), arg2.getClass().getSimpleName(), getAllowedExecutionTypes()));
        }
    }

    @Override
    public List<String> getAllowedExecutionTypes() {
        return Lists.newArrayList(Double.class.getSimpleName(), Integer.class.getSimpleName());
    }

}
