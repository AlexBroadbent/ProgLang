package uk.co.alexbroadbent.operator.list;

import uk.co.alexbroadbent.eval.ExpressionException;
import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.Literal;
import uk.co.alexbroadbent.eval.XList;
import uk.co.alexbroadbent.operator.IConstants;
import uk.co.alexbroadbent.operator.function.Function;

import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;

/**
 * x++.operator.function
 *
 * @author Alexander Broadbent
 * @version 29/03/2016
 */
public class Empty extends Function {

    @Override
    public String getToken() {
        return IConstants.EMPTY;
    }

    @Override
    public int getNumOperands() {
        return 1;
    }

    @Override
    public List<String> getAllowedExecutionTypes() {
        return Lists.newArrayList(LinkedList.class.getSimpleName());
    }

    @Override
    @SuppressWarnings( "unchecked" )    // Class Case Exception caught in method
    public Object execute(List<Literal> args) throws IncomparableTypeException, ExpressionException {
        if (args.size() != getNumOperands())
            throw new ExpressionException(String.format(MSG_ONE_ARG, args.size()));

        try {
            return ((XList) args.get(0).getValue()).isEmpty();
        }
        catch (ClassCastException | NullPointerException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), args.get(0));
        }
    }

}
