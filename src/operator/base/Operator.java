package operator.base;

import eval.ICalculableType;
import eval.Literal;
import eval.Variable;
import operator.IOperator;

import java.util.Stack;

/**
 * LazyLanguage.operator.base
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
        return getClass().getSimpleName();
    }


    public boolean isStackExecutable(Stack<Literal> stack) {
        for (Literal literal : stack)
            if (literal.getType() == ICalculableType.VARIABLE)
                if (!((Variable) literal).isValueSet())
                    return false;

        return true;
    }

}
