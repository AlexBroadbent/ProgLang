package operator.function;

import com.google.common.collect.Lists;
import eval.Literal;
import gui.XLogger;
import operator.IConstants;
import parser.ExpressionException;

import java.util.LinkedList;
import java.util.List;

/**
 * x++.operator.function
 *
 * @author Alexander Broadbent
 * @version 26/02/2016
 */
public class Cons extends Function {

    private final static String MSG_ARGS   = "Cons function requires two arguments: a literal and a list.";
    private final static String MSG_FORMAT = "The function must be in the format: cons(literal, list). Instead of list, found: %s.";

    @Override
    public String getToken() {
        return IConstants.CONS;
    }

    @Override
    @SuppressWarnings( "unchecked" )    // Class cast exception is caught
    public Object execute(List<Literal> args) throws ExpressionException {
        if (args.size() != 2)
            throw new ExpressionException(MSG_ARGS);

        LinkedList<Literal> list;
        LinkedList<Literal> newList = Lists.newLinkedList();

        // arg first, only take two
        try {
            list = (LinkedList<Literal>) args.get(1).getValue();
            newList.add(args.get(0));
            newList.addAll(list);
        }
        catch (ClassCastException ex) {
            XLogger.severe(""
                    + args.get(0).getValue().getClass().getSimpleName());
        }

        return newList;
    }

}
