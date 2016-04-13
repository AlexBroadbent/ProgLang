package operator.list;

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
public class Cons extends Function {

    private final static String MSG_FORMAT = "The function must be in the format: cons(literal, list). Instead of list, found: %s.";

    @Override
    public String getToken() {
        return IConstants.CONS;
    }

    @Override
    public int getNumOperands() {
        return 2;
    }

    @Override
    @SuppressWarnings( "unchecked" )    // Class cast exception is caught
    public Object execute(List<Literal> args) throws ExpressionException, IncomparableTypeException {
        if (args.size() != getNumOperands())
            throw new ExpressionException(String.format(MSG_SIZE_ARG, getNumOperands(), args.size()));

        XList list;
        XList newList = new XList();

        try {
            list = (XList) args.get(1).getValue();
            newList.push(args.get(0));
            newList.pushAll(list);

            return newList;
        }
        catch (ClassCastException | NullPointerException ex) {
            throw new IncomparableTypeException(String.format(MSG_FORMAT, args.get(0).getValue().getClass().getSimpleName() +
                    ", " + args.get(1).getValue().getClass().getSimpleName()));
        }
    }

}
