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
    public Object execute(List<Literal> args) throws ExpressionException {
        if (args.size() != getNumOperands())
            throw new ExpressionException("Tail function requires only one argument");

        LinkedList<Literal> list = null;

        try {
            list = (LinkedList<Literal>) args.get(0).getValue();
        }
        catch (ClassCastException ex) {
            XLogger.severe("Argument given to head must be a list. Instead found: " + args.get(0).getValue().getClass().getSimpleName());
        }

        return (list != null) ? Lists.newLinkedList(list.subList(1, list.size())) : null;
    }
}
