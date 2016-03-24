package operator.function;

import eval.FunctionPlaceholder;
import eval.ICalculable;
import eval.Literal;
import model.Domain;
import operator.IConstants;
import operator.IOperator;
import parser.ExpressionException;
import parser.IncomparableTypeException;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static eval.ICalculableType.FUNCTION_PLACEHOLDER;
import static eval.ICalculableType.LITERAL;

/**
 * x++.operator.function
 *
 * @author Alexander Broadbent
 * @version 08/03/2016
 */
public class Declaration extends Function {

    @Override
    public String getToken() {
        return IConstants.FUNC;
    }

    /**
     * Evaluate method:
     * Pop off the arguments including the name, which will also be lexed as a variable, and
     * take the top argument as the name of the function - removing the variable from the domain.
     * <p>
     * Then create a UserFunction from the rest of the arguments, allowing the expression to be
     * set in the assignment.
     */
    @Override
    public Literal evaluate(Domain domain, Stack<Literal> stack, boolean returnExpression) throws IncomparableTypeException, ExpressionException {
        Stack<Literal> arguments = new Stack<>();
        while (!stack.isEmpty() && stack.peek().getType() != FUNCTION_PLACEHOLDER)
            arguments.push(stack.pop());
        stack.pop(); // pop the placeholder

        if (arguments.size() < 2)
            throw new ExpressionException("Function declaration must be in the format: func name(args) = expression");

        Literal nameArg = arguments.pop();
        if (nameArg.getType() != LITERAL)
            throw new ExpressionException("Expected a literal type at the start of the function declaration, instead found: " + nameArg.getValue().getClass().getSimpleName());
        String name = nameArg.getValue().toString();

        Collections.reverse(arguments);
        return Domain.wrapLiteral(new UserFunction(domain, name, arguments));
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        postfix.add(new FunctionPlaceholder());
        operatorStack.push(this);
    }

}
