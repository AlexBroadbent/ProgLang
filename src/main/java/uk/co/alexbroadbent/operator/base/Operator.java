package uk.co.alexbroadbent.operator.base;

import uk.co.alexbroadbent.eval.ICalculableType;
import uk.co.alexbroadbent.operator.IOperator;

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
