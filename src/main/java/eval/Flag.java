package eval;

import model.Domain;

import java.util.Stack;

/**
 * x++.eval
 *
 * @author Alexander Broadbent
 * @version 09/04/2016
 */
public class Flag extends Literal {

    public Flag() {
        super(null);
    }


    @Override
    public Literal evaluate(Domain domain, Stack stack, boolean returnExpression) {
        return null;
    }

    @Override
    public int getType() {
        return ICalculableType.FLAG;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
