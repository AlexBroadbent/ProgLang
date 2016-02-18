package operator.function;

import com.google.common.collect.Lists;
import eval.ICalculable;
import eval.ICalculableType;
import eval.Literal;
import model.Domain;
import operator.Associativity;
import operator.IOperator;
import operator.IPrecedence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * LazyLanguage.operator.function
 *
 * @author      Alexander Broadbent
 * @version     02/12/2015
 */
public class Function implements IOperator {

    @Override
    public String getToken() {
        String className = this.getClass().getSimpleName();
        return Character.toLowerCase(className.charAt(0)) + className.substring(1);
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
    public boolean isValidContext(Stack<IOperator> operatorStack, List<ICalculable> infix, int position) {
        return true;
    }

    @Override
    public int getType() {
        return ICalculableType.FUNCTION_OPERATOR;
    }

    @Override
    public List<String> getAllowedExecutionTypes() {
        return Lists.newArrayList(Double.class.getSimpleName(), Integer.class.getSimpleName());
    }

    @Override
    public Literal evaluate(Domain domain, Stack<Literal> stack) {
        ArrayList<Literal> args = Lists.newArrayList();

        for (int i=0; i<getNumOperands(); i++)
            args.add(stack.pop());

        Collections.reverse(args);
        return Domain.wrapLiteral(execute(args));
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        operatorStack.push(this);
    }

    public Object execute(ArrayList<Literal> args) {
        throw new Error("Function not implemented");
    }



}
