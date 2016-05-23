package operator.function;

import eval.IncomparableTypeException;
import eval.Literal;
import eval.NoValueException;
import eval.XList;

import java.util.List;
import java.util.Objects;

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
    public Object execute(List<Literal> args) throws IncomparableTypeException, NoValueException {
        double sum = 0;

        for (Literal literal : args) {
            try {
                if (Objects.equals(literal.getValue().getClass().getSimpleName(), XList.class.getSimpleName()))
                    for (Literal lit : ((XList) literal.getValue()))
                        sum += Double.parseDouble(lit.getValue().toString());
                else
                    sum += Double.parseDouble(literal.getValue().toString());
            }
            catch (NumberFormatException ex) {
                throw new IncomparableTypeException(getAllowedExecutionTypes(), literal);
            }
        }

        return sum;
    }

}
