package operator.function;

import com.google.common.collect.Lists;
import eval.IncomparableTypeException;
import eval.Literal;
import eval.XList;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
