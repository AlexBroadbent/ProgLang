package eval;

import com.google.common.collect.Lists;
import lexer.Token;
import lexer.UnknownSequenceException;
import model.Domain;
import operator.IOperator;
import org.apache.commons.lang3.StringUtils;
import parser.ExpressionException;
import parser.IncomparableTypeException;

import java.util.List;
import java.util.Stack;

/**
 * LazyLanguage.eval
 * <p>
 * Used for the body of a Lambda Expression.
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class Expression extends Literal {

    protected Domain model;
    protected List<ICalculable> expression;
    protected String infix;


    /**
     * Create an expression object...
     *
     * @param model  The domain object
     * @param tokens A lexed(sp?) list of Token objects representing the expression
     * @throws ExpressionException
     * @throws UnknownSequenceException
     */
    public Expression(Domain model, List<Token> tokens) throws ExpressionException, UnknownSequenceException {
        super(null);

        infix = StringUtils.join(tokens, " ");
        this.model = model;

        List<ICalculable> infixExpression = model.getParser().parse(model, tokens);

        init(infixExpression);

        if (!validate())
            throw new ExpressionException("Invalid Expression: " + expression);
    }


    /**
     * @param infix A parsed expression in infix form
     */
    public void init(List<ICalculable> infix) {
        Stack<IOperator> operatorStack = new Stack<>();
        expression = Lists.newArrayList();

        // 'A practical infix-to-prefix algorithm for mathematical expressions'
        // See http://www.chris-j.co.uk/parsing.php

        // sort through input infix
        for (int i = 0; i < infix.size(); i++)
            infix.get(i).toPostFix(infix, i, expression, operatorStack);

        // Handle any remaining tokens in operator stack
        while (operatorStack.size() > 0)
            expression.add(operatorStack.pop());
    }

    /**
     * Evaluate the post-fix infix
     * See http://en.wikipedia.org/wiki/Postfix_notation#The_postfix_algorithm
     * See https://en.wikipedia.org/wiki/Reverse_Polish_notation#Postfix_algorithm
     *
     * @return An object representing the value of the expression
     * @throws ExpressionException
     * @throws IncomparableTypeException
     */
    public Object execute() throws ExpressionException, IncomparableTypeException {

        // Escape clause for when just a single variable has been input to print the value
        if (expression.size() == 1 && expression.get(0).getType() == ICalculableType.VARIABLE)
            return ((Literal) expression.get(0)).getValue();

        Stack<Literal> stack = new Stack<>();
        for (ICalculable literal : expression)
            stack.push(literal.evaluate(model, stack));

        // get result from stack
        if (stack.size() == 1)
            return stack.pop().getValue();

        throw new ExpressionException("Invalid Expression: " + infix + " \nPostfix: " + StringUtils.join(expression, " "));
    }

    public boolean validate() {
        int stackSize = 0;
        for (ICalculable literal : expression) {
            stackSize++;

            if (literal instanceof IOperator)
                stackSize -= ((IOperator) literal).getNumOperands();
        }

        return stackSize == 1;
    }

    /**
     * <p>
     * Determines if the expression can be executed. The following conditions must be met:
     * <ul style='margin-top: 0;'>
     * <li>Variables must have a value set</li>
     * </ul>
     * <p>
     * </p>
     *
     * @return executable
     */
    public boolean isExecutable() {
        for (ICalculable literal : expression)
            if (literal.getType() == ICalculableType.VARIABLE)
                if (!((Variable) literal).isValueSet())
                    return false;

        return true;
    }


    @Override
    public String toString() {
        return infix;
    }

}
