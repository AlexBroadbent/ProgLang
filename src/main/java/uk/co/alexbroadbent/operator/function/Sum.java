package uk.co.alexbroadbent.operator.function;

import static uk.co.alexbroadbent.operator.IConstants.SUM;

import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.Literal;
import uk.co.alexbroadbent.eval.XList;

import java.util.List;
import java.util.Objects;

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
