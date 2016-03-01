package operator.function;

import com.google.common.collect.Lists;
import eval.ICalculable;
import eval.ICalculableType;
import eval.Literal;
import model.Domain;
import operator.Associativity;
import operator.IFunction;
import operator.IOperator;
import operator.IPrecedence;
import parser.IncomparableTypeException;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * LazyLanguage.operator.function
 *
 * @author      Alexander Broadbent
 * @version     02/12/2015
 */
public class Function implements IFunction {

    protected int operands = 0;

    @Override
    public String getToken() {
        String className = this.getClass().getSimpleName();
        return Character.toLowerCase(className.charAt(0)) + className.substring(1);
    }

    @Override
    public int getNumOperands() {
        return operands;
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
    public int getType() {
        return ICalculableType.FUNCTION;
    }

    @Override
    public List<String> getAllowedExecutionTypes() {
        return Lists.newArrayList(Double.class.getSimpleName(), Integer.class.getSimpleName());
    }

    @Override
    public boolean isValidContext(Stack<IOperator> operatorStack, List<ICalculable> infix, int position) {
        return true;
    }

    @Override
    public Literal evaluate(Domain domain, Stack<Literal> stack) throws IncomparableTypeException {
        List<Literal> args = Lists.newArrayList();

        while (stack.peek().getValue() != null)
            args.add(stack.pop());
        stack.pop(); // Pop off the arg separator
        operands = args.size();

        Collections.reverse(args);
        return Domain.wrapLiteral(execute(args));
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        postfix.add(new ArgSeparator());
        operatorStack.push(this);
    }

    public Object execute(List<Literal> args) throws IncomparableTypeException {
        throw new Error("Function not implemented");
    }

}
