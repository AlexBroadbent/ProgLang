package operator.common;

import com.google.common.collect.Lists;
import eval.*;
import model.Domain;
import operator.IConstants;
import operator.IPrecedence;
import operator.base.BinaryOperator;
import operator.function.UserFunction;
import parser.ExpressionException;
import parser.IncomparableTypeException;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static eval.ICalculableType.LITERAL;
import static eval.ICalculableType.VARIABLE;

/**
 * x++.operator.common
 *
 * @author Alexander Broadbent
 * @version 02/02/2016
 */
public class Assignment extends BinaryOperator {

    @Override
    public String getToken() {
        return IConstants.ASSIGNMENT;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.ASSIGNMENT_OPERATOR;
    }

    @Override
    public int getType() {
        return ICalculableType.OPERATOR;
    }

    @Override
    public Literal evaluate(Domain domain, Stack<Literal> stack, boolean returnExpression) throws IncomparableTypeException, ExpressionException {
        if (stack.size() > 2) {
            List<Literal> expression = Lists.newArrayList();
            while (!stack.isEmpty())
                expression.add(stack.pop());
            Collections.reverse(expression);

            Literal userFunction = expression.get(0);
            if (((UserFunction) userFunction.getValue()).getType() != ICalculableType.USER_FUNCTION)
                throw new ExpressionException("Invalid Assignment, expecting user function and found: " + userFunction.getValue().getClass().getSimpleName());

            List<ICalculable> expr = Lists.newArrayList(expression.subList(1, expression.size()));
            UserFunction func = (UserFunction) userFunction.getValue();

            try {
                if (!func.validate(expr, domain))
                    throw new ExpressionException("Function is not valid: Check the arguments match those used the expression");
            }
            catch (InvalidFunctionException ex) {
                throw new ExpressionException("Invalid Function: " + ex.getMessage());
            }
            // Remove operators from Literals
            for (int i = 0; i < expr.size(); i++) {
                ICalculable calculable = expr.get(i);
                if (calculable.getType() == LITERAL)
                    expr.set(i, (ICalculable) ((Literal) calculable).getValue());
            }
            func.setExpression(new Expression(expr, domain));
            domain.registerFunction(func);

            return Domain.wrapLiteral(null);
        }
        Literal arg2 = stack.pop();
        Literal arg1 = stack.pop();
        return Domain.wrapLiteral(execute(arg1, arg2));
    }

    @Override
    public Object execute(Literal arg1, Literal arg2) throws IncomparableTypeException, ExpressionException {
        if (arg1.getType() != VARIABLE)
            throw new ExpressionException("Assignment must be made to a variable");

        if (((Variable) arg1).isValueSet())
            throw new ExpressionException("Cannot change the value of variable once it is set");

        arg1.setValue(arg2.getValue());

        return null;
    }

}
