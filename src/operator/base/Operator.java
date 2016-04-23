package operator.base;

import eval.ICalculableType;
import operator.IOperator;

/**
 * x++.operator.base
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public abstract class Operator implements IOperator {

    @Override
    public int getType() {
        return ICalculableType.OPERATOR;
    }

    @Override
    public String toString() {
        return getToken();
    }

}
