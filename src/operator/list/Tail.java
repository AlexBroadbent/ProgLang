package operator.list;

import com.google.common.collect.Lists;
import eval.ExpressionException;
import eval.IncomparableTypeException;
import eval.Literal;
import eval.XList;
import operator.IConstants;
import operator.function.Function;

import java.util.List;

/**
 * x++.operator.function
 *
 * @author Alexander Broadbent
 * @version 26/02/2016
 */
public class Tail extends Function {

    @Override
    public String getToken() {
        return IConstants.TAIL;
    }

    @Override
    public int getNumOperands() {
        return 1;
    }

    @Override
    public List<String> getAllowedExecutionTypes() {
        return Lists.newArrayList(List.class.getSimpleName());
    }

    @Override
    @SuppressWarnings( "unchecked" )    // Catch is in place to check a casting exception
    public Object execute(List<Literal> args) throws ExpressionException, IncomparableTypeException {
        if (args.size() != getNumOperands())
            throw new ExpressionException(String.format(MSG_ONE_ARG, args.size()));

        XList list;

        try {
            list = (XList) args.get(0).getValue();
            return list.subList(1, list.size());
        }
        catch (ClassCastException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), args.get(0).getValue().getClass().getSimpleName());
        }
    }

}
