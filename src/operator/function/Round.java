package operator.function;

import eval.ExpressionException;
import eval.IncomparableTypeException;
import eval.Literal;
import operator.IConstants;

import java.util.List;

/**
 * ProgLang.operator.function
 *
 * @author Alexander Broadbent
 * @version 21/04/2016
 */
public class Round extends Function {

    @Override
    public String getToken() {
        return IConstants.ROUND;
    }

    @Override
    public int getNumOperands() {
        return 1;
    }

    @Override
    public Object execute(List<Literal> args) throws IncomparableTypeException, ExpressionException {
        if (args.size() != getNumOperands())
            throw new ExpressionException(String.format(MSG_SIZE_ARG, getNumOperands(), args.size()));

        try {
            Double arg = Double.parseDouble(args.get(0).getValue().toString());
            return Math.round(arg);
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), args.get(0));
        }
    }

}
