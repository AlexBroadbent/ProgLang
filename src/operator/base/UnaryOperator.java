package operator.base;

import com.google.common.collect.Lists;
import eval.*;
import model.Domain;
import operator.Associativity;
import operator.IOperator;
import operator.IPrecedence;
import operator.loop.In;

import java.util.List;
import java.util.Stack;

import static eval.ICalculableType.VARIABLE;

/**
 * x++.operator.base
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public abstract class UnaryOperator extends Operator {

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public int getNumOperands() {
        return 1;
    }

    @Override
    public Associativity getAssociativity() {
        return Associativity.LEFT_TO_RIGHT;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.NONE;
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

        Literal arg1 = stack.pop();

        // Assert that the operation is not attempting to be executed on a variable that does not have a value
        if (arg1.getType() == VARIABLE)
            if (!((Variable) arg1).isValueSet() && getClass() != In.class)
                throw new ExpressionException(String.format(MSG_VALUE_NOT_SET, ((Variable) arg1).getName()));

        return Domain.wrapLiteral(execute(arg1));
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        if (getAssociativity() == Associativity.LEFT_TO_RIGHT)
            operatorStack.push(this);   // pre-fix unary operator, add to operator stack
        if (getAssociativity() == Associativity.RIGHT_TO_LEFT)
            postfix.add(this);          // post-fix unary operator, add to postfix output
    }

    public Object execute(Literal arg1) throws IncomparableTypeException, ExpressionException, NoValueException {
        throw new ExpressionException("Operator not implemented");
    }

}
