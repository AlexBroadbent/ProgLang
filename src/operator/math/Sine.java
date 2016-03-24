package operator.math;

import eval.Literal;
import operator.IPrecedence;
import operator.function.Function;
import parser.IncomparableTypeException;

import java.util.List;

import static operator.IConstants.SIN;

/**
 * x++.operator.common
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class Sine extends Function {

    @Override
    public String getToken() {
        return SIN;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.FUNCTION;
    }

    @Override
    public Object execute(List<Literal> args) throws IncomparableTypeException {
        if (args.size() != 1)
            throw new Error("Sine function expected only one argument but received " + args.size());

        Literal arg1 = args.get(0);
        try {
            return Math.sin(Double.parseDouble(arg1.getValue().toString()));
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), arg1.getClass().getSimpleName());
        }
    }

}
