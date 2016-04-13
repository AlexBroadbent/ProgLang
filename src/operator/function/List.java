package operator.function;

import com.google.common.collect.Lists;
import eval.IncomparableTypeException;
import eval.Literal;
import eval.Variable;
import eval.XList;
import model.Domain;

import static operator.IConstants.LIST;

/**
 * x++.operator.function
 *
 * @author Alexander Broadbent
 * @version 26/02/2016
 */
public class List extends Function {

    @Override
    public String getToken() {
        return LIST;
    }

    @Override
    public java.util.List<String> getAllowedExecutionTypes() {
        return Lists.newArrayList(Integer.class.getSimpleName(), Double.class.getSimpleName(),
                Boolean.class.getSimpleName(), Variable.class.getSimpleName(), List.class.getSimpleName());
    }

    @Override
    public Object execute(java.util.List<Literal> args) throws IncomparableTypeException {
        return Domain.wrapLiteral(new XList(args));
    }
}
