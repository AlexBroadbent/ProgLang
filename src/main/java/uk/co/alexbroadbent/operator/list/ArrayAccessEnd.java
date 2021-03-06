package uk.co.alexbroadbent.operator.list;

import uk.co.alexbroadbent.eval.ExpressionException;
import uk.co.alexbroadbent.eval.ICalculableType;
import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.Literal;
import uk.co.alexbroadbent.eval.XList;
import uk.co.alexbroadbent.operator.IConstants;
import uk.co.alexbroadbent.operator.IPrecedence;
import uk.co.alexbroadbent.operator.base.BinaryOperator;

import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;

/**
 * x++.operator.list
 *
 * @author Alexander Broadbent
 * @version 26/03/2016
 */
public class ArrayAccessEnd extends BinaryOperator {

    @Override
    public String getToken() {
        return IConstants.ARRAY_ACCESS_END;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.ARRAY_ACCESS;
    }

    @Override
    public List<String> getAllowedExecutionTypes() {
        return Lists.newArrayList(LinkedList.class.getSimpleName());
    }

    @Override
    @SuppressWarnings( "unchecked" ) // Class cast exception caught
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException, ExpressionException {
        XList list;
        Integer position;

        try {
            list = (XList) arg1.getValue();
            position = Integer.parseInt(arg2.getValue().toString());

            if (list.size() < position)
                throw new ExpressionException("The list has a size of " + list.size() + ", position is not accessible");

            return list.get(position);
        }
        catch (ClassCastException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), arg1);
        }
        catch (NumberFormatException ex) {
            throw new ExpressionException("The position given is not a valid integer");
        }
    }

    @Override
    public int getType() {
        return ICalculableType.OPERATOR;
    }
}
