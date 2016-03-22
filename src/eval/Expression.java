package eval;

import com.google.common.collect.Lists;
import lexer.Token;
import lexer.UnknownSequenceException;
import model.Domain;
import operator.IOperator;
import operator.function.Declaration;
import org.apache.commons.lang3.StringUtils;
import parser.ExpressionException;
import parser.IncomparableTypeException;
import parser.ParserException;

import java.util.List;
import java.util.Stack;

import static eval.ICalculableType.*;

/**
 * x++.eval
 * <p>
 * Used for the body of a Lambda Expression.
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class Expression {

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
    public Expression(Domain model, List<Token> tokens) throws ExpressionException, UnknownSequenceException, ParserException {
        infix = StringUtils.join(tokens, " ");
        this.model = model;

        List<ICalculable> infixExpression = model.getParser().parse(model, tokens);

        init(infixExpression);

        if (!validate())
            throw new ExpressionException("Expression is not valid: " + this.toString());
    }

    public Expression(List<ICalculable> postfix, Domain model) throws ExpressionException {
        infix = StringUtils.join(postfix, " "); // TODO: postfix-to-infix function?
        this.model = model;

        init(postfix);

        if (!validate())
            throw new ExpressionException("Expression is not valid: " + this.toString());
    }


    /**
     * @param infix A parsed expression in infix form
     */
    protected void init(List<ICalculable> infix) {
        Stack<IOperator> operatorStack = new Stack<>();
        expression = Lists.newArrayList();

        // 'A practical infix-to-prefix algorithm for mathematical expressions'
        // See http://www.chris-j.co.uk/parsing.php

        // sort through input infix
        for (int i = 0; i < infix.size(); i++)
            infix.get(i).toPostFix(infix, i, expression, operatorStack);

        // Handle any remaining tokens in operator stack
        while (operatorStack.size() > 0) {
            expression.add(operatorStack.pop());
        }
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
        Stack<Literal> stack = new Stack<>();
        boolean isFunctionDeclaration = false;

        for (ICalculable literal : expression) {
            if (literal instanceof Declaration)
                isFunctionDeclaration = true;
            Literal result = literal.evaluate(model, stack, isFunctionDeclaration);
            if (result != null)
                stack.push(result);
        }

        // get result from stack
        if (stack.size() == 1)
            return stack.pop().getValue();

        throw new ExpressionException("Arguments remaining after execution: " + StringUtils.join(stack, ", "));
    }

    protected boolean validate() {
        int stackSize = 0;
        int funcSize = 0;
        int inFunc = 0;

        for (ICalculable literal : expression) {
            stackSize++;
            if (inFunc > 0)
                funcSize++;

            if (literal.getType() == OPERATOR)
                stackSize -= ((IOperator) literal).getNumOperands();
            if (literal.getType() == FUNCTION || literal.getType() == USER_FUNCTION) {
                inFunc--;
                stackSize -= funcSize;
                funcSize = 0;
            }
            if (literal.getType() == FUNCTION_PLACEHOLDER) {
                inFunc++;
            }
        }

        return stackSize == 1;
    }

    @Override
    public String toString() {
        return infix;
    }

}
