package operator.base;

import operator.IOperator;

/**
 * LazyLanguage.operator.base
 *
 * @version     01/12/2015
 * @author      Alexander Broadbent
 */
public abstract class Operator implements IOperator {

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
