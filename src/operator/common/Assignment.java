package operator.common;

import com.google.common.collect.Lists;
import eval.*;
import model.Domain;
import operator.IConstants;
import operator.IPrecedence;
import operator.IUserFunction;
import operator.base.BinaryOperator;
import operator.function.UserFunction;
import parser.ExpressionException;
import parser.IncomparableTypeException;

import java.util.List;
import java.util.Stack;

import static eval.ICalculableType.VARIABLE;

/**
 * x++.operator.common
 *
 * @author Alexander Broadbent
 * @version 02/02/2016
 */
public class Assignment extends BinaryOperator {

    private final static String MSG_INVALID_ASSIGNMENT = "Invalid Assignment, expecting user function and found: %s";
    private final static String MSG_INVALID_FUNCTION   = "Function is not valid: Check the arguments match those used the expression";

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
            // Copy stack into a List to have cleaner operations
            List<Literal> expression = Lists.newArrayList(stack);
            stack.clear();

            // Validate that a UserFunction object exists in the top of the stack
            Literal userFuncLiteral = expression.get(0);
            if (((ICalculable) userFuncLiteral.getValue()).getType() != ICalculableType.USER_FUNCTION)
                throw new ExpressionException(String.format(MSG_INVALID_ASSIGNMENT, userFuncLiteral.getValue().getClass().getSimpleName()));
            IUserFunction userFunction = (UserFunction) userFuncLiteral.getValue();

            // Check expression is valid for the function, such as matching arguments
            List<ICalculable> expr = Lists.newArrayList(expression.subList(1, expression.size()));
            userFunction.setExpression(new Expression(expr, domain));
            if (!userFunction.validate())
                throw new ExpressionException(MSG_INVALID_FUNCTION);

            // Set the expression of the function and register the function in the domain
            userFunction.setExpression(new Expression(expr, domain));

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
            throw new ExpressionException("Assignment must be made to a variable");

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
