package parser;

import com.google.common.collect.Lists;
import eval.Flag;
import eval.ICalculable;
import eval.Literal;
import lexer.Token;
import model.Domain;
import operator.IUserFunction;
import operator.function.UserFunction;

import java.util.List;
import java.util.Objects;

import static lexer.IToken.FUNCTION_DECLARATION;
import static lexer.IToken.VARIABLE_FUNCTION;
import static operator.IConstants.*;

/**
 * x++.parser
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class Parser implements IParser {

    private static final String MSG_NAME_EXISTS  = "The function already exists, use another name.";
    private static final String MSG_INVALID_FUNC = "Invalid sequence, check the syntax of function declarations";


    /**
     * Parses a list of tokens into the corresponding ICalculable classes
     *
     * @param domain domain model
     * @param tokens list of tokens
     * @return list of ICalculable objects
     * @throws ParserException when an unexpected token is analysed
     */
    @Override
    public List<ICalculable> parse(Domain domain, List<Token> tokens) throws ParserException {
        List<ICalculable> infixExpression = Lists.newArrayList();

        if (tokens.get(0).token == FUNCTION_DECLARATION)
            return parseDeclaration(domain, tokens.subList(1, tokens.size()));          // Function declaration
        if (Objects.equals(tokens.get(0).sequence, FOR_LOOP))
            return parseForLoop(domain, tokens.subList(1, tokens.size()));              // For loop declaration

        for (Token token : tokens) {
            if (domain.isOperator(token.sequence))
                infixExpression.add(domain.getOperator(token.sequence));                // Operator
            else if (domain.isFunction(token.sequence))
                infixExpression.add(domain.getFunction(token.sequence));                // Function
            else if (token.token == VARIABLE_FUNCTION)
                infixExpression.add(domain.getOrCreateVariable(token.sequence));        // Variable
            else
                infixExpression.add(Literal.parseLiteral(token));                       // Constant
        }

        return infixExpression;
    }

    /**
     * Parse a list of tokens that define a function declaration. Return a list of ICalculable objects
     * that represent the expression.
     *
     * @param domain domain model
     * @param tokens list of tokens
     * @return list of ICalculable objects
     */
    private List<ICalculable> parseDeclaration(Domain domain, List<Token> tokens) throws ParserException {
        List<ICalculable> infixExpression = Lists.newArrayList(new Flag());
        boolean inFunctionAssignment = false;
        IUserFunction function = null;

        for (Token token : tokens) {
            if (domain.isOperator(token.sequence)) {
                if (Objects.equals(token.sequence, ASSIGNMENT))                         // Operator
                    inFunctionAssignment = true;
                if (inFunctionAssignment)
                    infixExpression.add(domain.getOperator(token.sequence));
            }
            else if (domain.isFunction(token.sequence)) {
                if (function == null && !Objects.equals(token.sequence, FUNC))          // Function
                    throw new ParserException(MSG_NAME_EXISTS);
                if (Objects.equals(token.sequence, FOR_LOOP))
                    infixExpression.addAll(parseForLoop(domain, tokens.subList(tokens.indexOf(token) + 1, tokens.size())));

                infixExpression.add(domain.getFunction(token.sequence));
            }
            else if (token.token == VARIABLE_FUNCTION) {
                if (!inFunctionAssignment) {                                            // Variable
                    if (function == null) {
                        function = new UserFunction(domain, token.sequence);
                        domain.registerFunction(function);
                        infixExpression.add(function);
                    }
                    else
                        function.addArgument(domain.addFunctionalVariable(function.getName(), token.sequence));
                }
                else {
                    if (function != null && Objects.equals(token.sequence, function.getName()))
                        infixExpression.add(domain.getFunction(function.getName()));
                    else {
                        if (function != null)
                            infixExpression.add(domain.getFunctionalVariable(function.getName(), token.sequence));
                        else
                            throw new ParserException(MSG_INVALID_FUNC);
                    }
                }
            }
            else
                infixExpression.add(Literal.parseLiteral(token));                       // Constant
        }

        return infixExpression;
    }


    private List<ICalculable> parseForLoop(Domain domain, List<Token> tokens) {


        return Lists.newArrayList();
    }

}
