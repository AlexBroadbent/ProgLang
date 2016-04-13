package operator.function;

import eval.IncomparableTypeException;
import eval.Literal;
import eval.Variable;

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
    public List<String> getAllowedExecutionTypes() {
        List<String> sup = super.getAllowedExecutionTypes();
        sup.add(Variable.class.getSimpleName());
        return sup;
    }

    @Override
    public Object execute(List<Literal> args) throws IncomparableTypeException {
        double sum = 0;

        Literal lit = null;
        try {
            for (Literal literal : args) {
                lit = literal;
                sum += Double.parseDouble(literal.getValue().toString());
            }
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), lit.getValue().getClass().getSimpleName());
        }

        return sum;
    }

}
