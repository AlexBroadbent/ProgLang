package operator.common;

import eval.ICalculableType;
import eval.Literal;
import eval.Variable;
import operator.IConstants;
import operator.IPrecedence;
import operator.base.BinaryOperator;
import parser.ExpressionException;
import parser.IncomparableTypeException;

import static eval.ICalculableType.VARIABLE;

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
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException, ExpressionException {
        if (arg1.getType() != VARIABLE)
            throw new ExpressionException("Assignment must be made to a variable");

        if (((Variable) arg1).isValueSet())
            throw new ExpressionException("Cannot change the value of variable once it is set");

        arg1.setValue(arg2.getValue());

        return null;
    }

}
