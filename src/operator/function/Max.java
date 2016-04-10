package operator.function;

import com.google.common.collect.Lists;
import eval.Literal;
import parser.IncomparableTypeException;

import java.util.Collections;
import java.util.List;

import static operator.IConstants.MAX;

/**
 * x++.operator.function
 *
 * @author Alexander Broadbent
 * @version 26/02/2016
 */
public class Max extends Function {

    @Override
    public String getToken() {
        return MAX;
    }

    @Override
    public Object execute(List<Literal> args) throws IncomparableTypeException {
        List<Double> list = Lists.newArrayList();

        Literal lit = null;
        try {
            for (Literal literal : args) {
                lit = literal;
                list.add(Double.parseDouble(literal.getValue().toString()));
            }
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), lit.getValue().getClass().getSimpleName());
        }

        return Collections.max(list);
    }

}
