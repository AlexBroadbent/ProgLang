package operator.list;

import com.google.common.collect.Lists;
import eval.Literal;
import operator.IConstants;
import operator.function.Function;
import parser.ExpressionException;
import parser.IncomparableTypeException;

import java.util.LinkedList;
import java.util.List;

/**
 * ProgLang.operator.function
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

        List<Literal> list;
        try {
            list = (List<Literal>) args.get(0).getValue();
        }
        catch (ClassCastException | NullPointerException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), args.get(0).getValue().getClass().getSimpleName());
        }

        return list.isEmpty();
    }

}
