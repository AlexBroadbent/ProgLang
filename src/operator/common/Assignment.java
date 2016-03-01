package operator.common;

import eval.ICalculableType;
import eval.Literal;
import operator.IConstants;
import operator.IPrecedence;
import operator.base.BinaryOperator;
import parser.IncomparableTypeException;

/**
 * ProgLang.operator.common
 *
 * @author      Alexander Broadbent
 * @version     02/02/2016
 */
public class Assignment extends BinaryOperator {

    @Override
    public String getToken() {
        return IConstants.ASSIGNMENT;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.ASSIGNMENT_OPERATOR;
    }

    @Override
    public int getType() {
        return ICalculableType.OPERATOR;
    }

    @Override
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException {
        arg1.setValue(arg2.getValue());
        return "";
    }

}
