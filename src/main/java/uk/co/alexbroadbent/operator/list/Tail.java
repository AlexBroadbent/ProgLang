package uk.co.alexbroadbent.operator.list;

import uk.co.alexbroadbent.eval.ExpressionException;
import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.Literal;
import uk.co.alexbroadbent.eval.XList;
import uk.co.alexbroadbent.operator.IConstants;
import uk.co.alexbroadbent.operator.function.Function;

import com.google.common.collect.Lists;

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
            throw new IncomparableTypeException(getAllowedExecutionTypes(), args.get(0));
        }
    }

}
