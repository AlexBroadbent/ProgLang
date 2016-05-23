package operator.function;

import com.google.common.collect.Lists;
import eval.*;
import model.Domain;
import operator.Associativity;
import operator.IFunction;
import operator.IOperator;
import operator.IPrecedence;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static eval.ICalculableType.FUNCTION_PLACEHOLDER;

/**
 * x++.operator.function
 *
 * @author Alexander Broadbent
 * @version 02/12/2015
 */
public abstract class Function implements IFunction {

    protected final static String MSG_ONE_ARG  = "Only one argument is required, instead found %d";
    protected final static String MSG_SIZE_ARG = "The function expects %d arguments, instead received %d";


    @Override
    public String getToken() {
        return getClass().getSimpleName();
    }

    @Override
    public int getNumOperands() {
        return 0;
    }

    @Override
    public Associativity getAssociativity() {
        return Associativity.LEFT_TO_RIGHT;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.FUNCTION;
    }

    @Override
    public List<String> getAllowedExecutionTypes() {
        return Lists.newArrayList(Double.class.getSimpleName(), Integer.class.getSimpleName());
    }

    @Override
    public Literal evaluate(Domain domain, Stack<Literal> stack, boolean returnExpression)
            throws IncomparableTypeException, ExpressionException, NoValueException {
        if (returnExpression)
            return Domain.wrapLiteral(this);

        List<Literal> args = Lists.newArrayList();

        // functionLevel defines how many functions deep the code is in, every function increments this number
        //  and a placeholder will decrement it.
        int functionLevel = 0;
        while ((!stack.isEmpty() && stack.peek().getType() != FUNCTION_PLACEHOLDER) || functionLevel > 0) {
            if (stack.peek().getValue() instanceof Function)
                functionLevel++;
            else if (stack.peek().getType() == FUNCTION_PLACEHOLDER)
                functionLevel--;

            args.add(stack.pop());
        }
        stack.pop(); // Pop off the arg separator

        Collections.reverse(args);
        return Domain.wrapLiteral(execute(args));
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        postfix.add(new FunctionPlaceholder());
        operatorStack.push(this);
    }

    @Override
    public int getType() {
        return ICalculableType.FUNCTION;
    }

    public Object execute(List<Literal> args) throws IncomparableTypeException, ExpressionException, NoValueException {
        throw new ExpressionException("Function not implemented");
    }

    @Override
    public String toString() {
        return getToken() + "(" + ((getNumOperands() == 0) ? "*" : getNumOperands()) + ")";
    }

}
