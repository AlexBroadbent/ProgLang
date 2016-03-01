package operator.function;

import com.google.common.collect.Lists;
import eval.Literal;
import parser.IncomparableTypeException;

import static operator.IConstants.LIST;

/**
 * ProgLang.operator.function
 *
 * @author      Alexander Broadbent
 * @version     26/02/2016
 */
public class List extends Function {

    @Override
    public String getToken() {
        return LIST;
    }

    @Override
    public int getNumOperands() {
        return operands;
    }

    @Override
    public Object execute(java.util.List<Literal> args) throws IncomparableTypeException {
        return new Literal(Lists.newLinkedList(args));
    }

}
