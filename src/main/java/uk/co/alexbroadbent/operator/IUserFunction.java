package uk.co.alexbroadbent.operator;

import uk.co.alexbroadbent.eval.Expression;
import uk.co.alexbroadbent.eval.ExpressionException;
import uk.co.alexbroadbent.eval.Variable;

import java.util.List;

/**
 * x++.operator
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public interface IUserFunction extends IFunction {

    String getName();

    void addArgument(Variable arg);

    List<Variable> getArguments();

    void setArguments(List<Variable> arguments);

    Expression getExpression();

    void setExpression(Expression expression);

    boolean validate() throws ExpressionException;

}
