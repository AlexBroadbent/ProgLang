package operator.function;

import eval.Literal;
import parser.IncomparableTypeException;

import java.util.List;

import static operator.IConstants.SUM;

/**
 * x++.operator.function
 *
 * @author Alexander Broadbent
 * @version 26/02/2016
 */
public class Sum extends Function {

    @Override
    public String getToken() {
        return SUM;
    }

    @Override
    public Object execute(List<Literal> args) throws IncomparableTypeException {
        double sum = 0;

        for (Literal literal : args)
            sum += Double.parseDouble(literal.getValue().toString());

        return sum;
    }

}
