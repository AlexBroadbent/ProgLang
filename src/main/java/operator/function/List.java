package operator.function;

import eval.IncomparableTypeException;
import eval.Literal;
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
    public Object execute(java.util.List<Literal> args) throws IncomparableTypeException {
        return Domain.wrapLiteral(new XList(args));
    }
}
