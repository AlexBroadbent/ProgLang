package operator.function;

import com.google.common.collect.Lists;
import eval.Literal;
import parser.IncomparableTypeException;

import java.util.Collections;
import java.util.List;

import static operator.IConstants.MAX;

/**
 * ProgLang.operator.function
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

        for (Literal literal : args)
            list.add(Double.parseDouble(literal.getValue().toString()));

        return Collections.max(list);
    }

    @Override
    public List<String> getAllowedExecutionTypes() {
        return Lists.newArrayList(Double.class.getSimpleName(), Integer.class.getSimpleName());
    }
}
