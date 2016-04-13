package operator.function;

import com.google.common.collect.Lists;
import eval.IncomparableTypeException;
import eval.Literal;
import gui.XLogger;

import static operator.IConstants.PRINT;

/**
 * x++.operator.function
 *
 * @author Alexander Broadbent
 * @version 26/02/2016
 */
public class Print extends Function {

    @Override
    public String getToken() {
        return PRINT;
    }

    @Override
    public java.util.List<String> getAllowedExecutionTypes() {
        return Lists.newArrayList(Literal.class.getSimpleName());
    }

    @Override
    public Object execute(java.util.List<Literal> args) throws IncomparableTypeException {
        XLogger.log(args.toString());
        return null;
    }

}
