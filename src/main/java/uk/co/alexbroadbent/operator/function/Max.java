package uk.co.alexbroadbent.operator.function;

import static uk.co.alexbroadbent.operator.IConstants.MAX;

import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.Literal;
import uk.co.alexbroadbent.eval.XList;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

        for (Literal literal : args) {
            try {
                if (Objects.equals(literal.getValue().getClass().getSimpleName(), XList.class.getSimpleName()))
                    for (Literal lit : ((XList) literal.getValue()))
                        list.add(Double.parseDouble(lit.getValue().toString()));
                else
                    list.add(Double.parseDouble(literal.getValue().toString()));
            }
            catch (NumberFormatException ex) {
                throw new IncomparableTypeException(getAllowedExecutionTypes(), literal);
            }
        }

        return Collections.max(list);
    }

}
