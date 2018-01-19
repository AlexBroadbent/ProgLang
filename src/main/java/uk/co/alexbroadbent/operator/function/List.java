package uk.co.alexbroadbent.operator.function;

import static uk.co.alexbroadbent.operator.IConstants.LIST;

import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.Literal;
import uk.co.alexbroadbent.eval.XList;
import uk.co.alexbroadbent.model.Domain;

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
