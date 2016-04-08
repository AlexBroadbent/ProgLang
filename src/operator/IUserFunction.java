package operator;

import eval.Expression;
import eval.Literal;
import parser.ExpressionException;

import java.util.List;

/**
 * x++.operator
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public interface IUserFunction extends IFunction {

    String getName();

    void addArgument(Literal arg);

    List<Literal> getArguments();

    void setArguments(List<Literal> arguments);

    Expression getExpression();

    void setExpression(Expression expression);

    boolean validate() throws ExpressionException;

}
