package uk.co.alexbroadbent.operator.common;

import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.Literal;
import uk.co.alexbroadbent.operator.IConstants;
import uk.co.alexbroadbent.operator.IPrecedence;
import uk.co.alexbroadbent.operator.base.BinaryOperator;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * x++.operator
 *
 * @author Alexander Broadbent
 * @version 31/12/2015
 */
public class LogicalAnd extends BinaryOperator {

    @Override
    public String getToken() {
        return IConstants.LOGICAL_AND;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.LOGICAL_AND;
    }

    @Override
    public List<String> getAllowedExecutionTypes() {
        return Lists.newArrayList(Boolean.class.getSimpleName());
    }

    @Override
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException {
        try {
            return ((Boolean) arg1.getValue()) && ((Boolean) arg2.getValue());
        }
        catch (ClassCastException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(),
                    getIncomparableObject(arg1, arg2, getAllowedExecutionTypes()));
        }
    }

}
