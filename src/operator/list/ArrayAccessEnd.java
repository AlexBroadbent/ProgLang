package operator.list;

import com.google.common.collect.Lists;
import eval.ICalculableType;
import eval.Literal;
import operator.IConstants;
import operator.IPrecedence;
import operator.base.BinaryOperator;
import parser.ExpressionException;
import parser.IncomparableTypeException;

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
    @SuppressWarnings( "unchecked" ) // Caught and warning issued
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException, ExpressionException {
        LinkedList<Literal> list = null;
        Integer position = null;

        try {
            list = (LinkedList<Literal>) arg1.getValue();
            position = Integer.parseInt(arg2.getValue().toString());
        }
        catch (ClassCastException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), arg1.getValue().getClass().getSimpleName());
        }
        catch (NumberFormatException ex) {
            throw new ExpressionException("The position given is not a valid integer");
        }

        if (list.size() < position)
            throw new ExpressionException("The list has a size of " + list.size() + ", position is not accessible");

        return list.get(position);
    }

    @Override
    public int getType() {
        return ICalculableType.OPERATOR;
    }
}
