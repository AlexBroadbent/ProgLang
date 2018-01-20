package uk.co.alexbroadbent.operator.common;

import static uk.co.alexbroadbent.eval.ICalculableType.VARIABLE;

import uk.co.alexbroadbent.eval.Expression;
import uk.co.alexbroadbent.eval.ExpressionException;
import uk.co.alexbroadbent.eval.ICalculable;
import uk.co.alexbroadbent.eval.ICalculableType;
import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.Literal;
import uk.co.alexbroadbent.eval.Variable;
import uk.co.alexbroadbent.model.Domain;
import uk.co.alexbroadbent.operator.IConstants;
import uk.co.alexbroadbent.operator.IPrecedence;
import uk.co.alexbroadbent.operator.IUserFunction;
import uk.co.alexbroadbent.operator.base.BinaryOperator;
import uk.co.alexbroadbent.operator.function.UserFunction;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Stack;

/**
 * x++.operator.common
 *
 * @author Alexander Broadbent
 * @version 02/02/2016
 */
public class Assignment extends BinaryOperator {

    private final static String MSG_INVALID_ASSIGNMENT = "Invalid Assignment, expecting user function and found: %s";
    private final static String MSG_INVALID_FUNCTION   = "Invalid Function: Check the passed variables match those used" +
            " in the expression";

    @Override
    public String getToken() {
        return IConstants.ASSIGNMENT;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.ASSIGNMENT_OPERATOR;
    }

    @Override
    public Literal evaluate(Domain domain, Stack<Literal> stack, boolean returnExpression)
            throws IncomparableTypeException, ExpressionException {

        // If the stack has more than two items then it is a function declaration
        if (stack.size() > 2) {
            // Remove the placeholder for the UserFunction
            stack.remove(0);

            // Validate that a UserFunction object exists in the top of the stack
            Literal userFuncLiteral = stack.get(0);
            if (((ICalculable) userFuncLiteral.getValue()).getType() != ICalculableType.USER_FUNCTION)
                throw new ExpressionException(String.format(MSG_INVALID_ASSIGNMENT, userFuncLiteral.getValue().getClass().getSimpleName()));
            IUserFunction userFunction = (UserFunction) userFuncLiteral.getValue();

            // Check expression is valid for the function, such as matching arguments
            List<ICalculable> expr = Lists.newArrayList(stack.subList(1, stack.size()));
            userFunction.setExpression(new Expression(expr, domain));
            if (!userFunction.validate())
                throw new ExpressionException(MSG_INVALID_FUNCTION);

            // Empty the stack
            stack.clear();

            return Domain.wrapLiteral(null);
        }

        Literal arg2 = stack.pop();
        Literal arg1 = stack.pop();
        return Domain.wrapLiteral(execute(arg1, arg2));
    }

    @Override
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException, ExpressionException {
        // Check that the first argument is a variable
        if (arg1.getType() != VARIABLE)
            throw new ExpressionException(""); // TODO: proper output for reserved names

        // Ensure that the variable is immutable by rejecting a change
        if (((Variable) arg1).isValueSet())
            throw new ExpressionException("Cannot change the value of variable once it is set");


        arg1.setValue(arg2.getValue());
        return null;
    }

    @Override
    public int getType() {
        return ICalculableType.OPERATOR;
    }

}
