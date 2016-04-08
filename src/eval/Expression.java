package eval;

import com.google.common.collect.Lists;
import gui.XLogger;
import lexer.Token;
import lexer.UnknownSequenceException;
import model.Domain;
import operator.IOperator;
import operator.conditional.Conditional;
import operator.conditional.ConditionalElse;
import operator.function.Declaration;
import operator.loop.Do;
import operator.loop.ForLoop;
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
public class Expression extends Literal {

    private final static String MSG_EXP_INVALID = "Expression is not valid: %s";
    private final static String MSG_ARG_REMAIN  = "Arguments remaining after executing: %s";
    protected Domain            model;
    protected List<ICalculable> expression;
    protected String            infix;
    /**
     * Create an expression object...
     *
     * @param model  The domain object
     * @param tokens A lexically analysed list of Token objects representing the expression
     * @throws ExpressionException
     * @throws UnknownSequenceException
     */
    public Expression(Domain model, List<Token> tokens)
            throws ExpressionException, UnknownSequenceException, ParserException {
        super(null);
        this.model = model;
        infix = StringUtils.join(tokens, " ");

        List<ICalculable> infixExpression = model.getParser().parse(model, tokens);

        init(infixExpression);

        if (!validate())
            throw new ExpressionException(String.format(MSG_EXP_INVALID, toString()));
    }


    public Expression(List<ICalculable> postfix, Domain model) throws ExpressionException {
        super(null);
        this.model = model;
        infix = StringUtils.join(postfix, " ");
        expression = parseWrappedList(postfix);

        if (!validate())
            throw new ExpressionException(String.format(MSG_EXP_INVALID, toString()));
    }

    public static List<ICalculable> parseWrappedList(List<ICalculable> postfix) {
        for (int i = 0; i < postfix.size(); i++) {
            ICalculable calculable = postfix.get(i);
            if (calculable.getType() == LITERAL) {
                if (((Literal) calculable).getValue() instanceof IOperator)
                    postfix.set(i, (ICalculable) ((Literal) calculable).getValue());
            }
            else
                postfix.set(i, calculable);
        }

        return postfix;
    }

    public List<ICalculable> getExpression() {
        return expression;
    }


    /**
     * 'A practical infix-to-prefix algorithm for mathematical expressions'
     * See http://www.chris-j.co.uk/parsing.php
     *
     * @param infix A parsed expression in infix form
     */
    private void init(List<ICalculable> infix) {
        Stack<IOperator> operatorStack = new Stack<>();
        expression = Lists.newArrayList();

        // sort through input infix and convert to postfix through expression variable
        for (int i = 0; i < infix.size(); i++)
            infix.get(i).toPostFix(infix, i, expression, operatorStack);

        // push any remaining tokens in operator stack
        while (operatorStack.size() > 0)
            expression.add(operatorStack.pop());
    }

    /**
     * Evaluate the post-fix infix
     * <p>
     * See http://en.wikipedia.org/wiki/Postfix_notation#The_postfix_algorithm
     * See https://en.wikipedia.org/wiki/Reverse_Polish_notation#Postfix_algorithm
     * <p>
     * When in either a function, for loop expression, or a conditional expression declaration
     * the result should be itself and not actually execute the operators/functions.
     *
     * @return An object representing the value of the expression
     * @throws ExpressionException
     * @throws IncomparableTypeException
     */
    public Object execute() throws ExpressionException, IncomparableTypeException {
        Stack<Literal> stack = new Stack<>();
        boolean inFuncDec = false;  // defining a function
        boolean inLoopDec = false;  // defining a for loop expression
        boolean inCondDec = false;  // defining a conditional expression

        for (ICalculable literal : expression) {
            if (literal instanceof Declaration)
                inFuncDec = true;
            else if (literal instanceof Do)
                inLoopDec = true;
            else if (literal instanceof ForLoop)
                inLoopDec = false;
            else if (literal.getType() == CONDITIONAL_PLACEHOLDER)
                inCondDec = true;
            else if (literal instanceof Conditional || literal instanceof ConditionalElse)
                inCondDec = false;

            Literal result = literal.evaluate(model, stack, (inFuncDec || inLoopDec || inCondDec));
            if (result != null)
                stack.push(result);
        }

        // get result from stack
        if (stack.size() == 1)
            return stack.pop().getValue();

        throw new ExpressionException(String.format(MSG_ARG_REMAIN, StringUtils.join(stack, ", ")));
    }

    private boolean validate() {
        int stackSize = 0;
        int funcSize = 0;
        int inFunc = 0;

        for (ICalculable literal : expression) {
            stackSize++;
            if (inFunc > 0)
                funcSize++;

            if (literal.getType() == OPERATOR && inFunc == 0)
                stackSize -= ((IOperator) literal).getNumOperands();
            else if (literal.getType() == FUNCTION || literal.getType() == USER_FUNCTION) {
                inFunc--;
                stackSize -= funcSize;
                funcSize = 0;
            }
            else if (literal.getType() == FUNCTION_PLACEHOLDER)
                inFunc++;
            else if (literal.getType() == CONDITIONAL_PLACEHOLDER)
                stackSize--;
        }

        return stackSize == 1;
    }

    @Override
    public Object getValue() {
        try {
            return execute();
        }
        catch (ExpressionException | IncomparableTypeException e) {
            XLogger.severe(e.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {
        return infix;
    }

}
