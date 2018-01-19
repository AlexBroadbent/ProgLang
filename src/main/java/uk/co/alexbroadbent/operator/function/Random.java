package uk.co.alexbroadbent.operator.function;

import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.Literal;

import java.util.List;

import static uk.co.alexbroadbent.operator.IConstants.RANDOM;

/**
 * x++.operator.function
 *
 * @author Alexander Broadbent
 * @version 07/04/2016
 */
public class Random extends Function {

    @Override
    public String getToken() {
        return RANDOM;
    }

    @Override
    public Object execute(List<Literal> args) throws IncomparableTypeException {
        return Math.random();
    }

}
