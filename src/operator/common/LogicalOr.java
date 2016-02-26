package operator.common;

import com.google.common.collect.Lists;
import eval.ICalculableType;
import eval.Literal;
import operator.IConstants;
import operator.IPrecedence;
import operator.base.BinaryOperator;
import parser.IncomparableTypeException;

import java.util.List;

/**
 * x++.operator
 *
 * @author Alexander Broadbent
 * @version 31/12/2015
 */
public class LogicalOr extends BinaryOperator {

    @Override
    public String getToken() {
        return IConstants.LOGICAL_OR;
    }

    @Override
    public int getType() {
        return ICalculableType.COMMON_OPERATOR;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.LOGICAL_OR;
    }

    @Override
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException {
        try {
            return Boolean.valueOf(arg1.getValue().toString()) || Boolean.valueOf(arg2.getValue().toString());
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(),
                    getIncomparableType(arg1.getClass().getSimpleName(), arg2.getClass().getSimpleName(), getAllowedExecutionTypes()));
        }
    }

    @Override
    public List<String> getAllowedExecutionTypes() {
        return Lists.newArrayList(Boolean.class.getSimpleName());
    }

}
