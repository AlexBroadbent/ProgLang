package parser;

import com.google.common.collect.Lists;
import eval.ICalculable;
import eval.Literal;
import eval.Variable;
import lexer.Token;
import model.Domain;
import operator.IUserFunction;
import operator.function.UserFunction;

import java.util.List;
import java.util.Objects;

import static lexer.IToken.FUNCTION_DECLARATION;
import static lexer.IToken.VARIABLE;
import static operator.IConstants.ASSIGNMENT;
import static operator.IConstants.FUNC;

/**
 * x++.parser
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class Parser implements IParser {

    /**
     * Parses a list of tokens into the corresponding ICalculable classes
     *
     * @param domain domain model
     * @param tokens list of tokens
     * @return list of ICalculable objects
     * @throws ParserException
     */
    @Override
    public List<ICalculable> parse(Domain domain, List<Token> tokens) throws ParserException {
        List<ICalculable> infixExpression = Lists.newArrayList();

        for (Token token : tokens) {
            if (token.token == FUNCTION_DECLARATION)
                return parseDeclaration(domain, tokens);                                        // Function declaration
            if (token.token == VARIABLE)
                infixExpression.add(domain.getOrCreateVariable(token.sequence));                            // Variable
            else if (domain.isOperator(token.sequence))
                infixExpression.add(domain.getOperator(token.sequence));                                    // Operator
            else if (domain.isFunction(token.sequence))
                infixExpression.add(domain.getFunction(token.sequence));                                    // Function
            else
                infixExpression.add(Literal.parseLiteral(token));                                           // Constant
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
        List<ICalculable> infixExpression = Lists.newArrayList();
        boolean inFunctionAssignment = false;
        IUserFunction function = null;

        for (Token token : tokens) {
            if (token.token == VARIABLE) {
                if (!inFunctionAssignment) {                                                                // Variable
                    if (function == null) {
                        function = new UserFunction(domain, token.sequence);
                        domain.registerFunction(function);
                        infixExpression.add(Domain.wrapLiteral(function));
                    }
                    else {
                        Variable variable = domain.addFunctionalVariable(function.getName(), token.sequence);
                        function.addArgument(Domain.wrapLiteral(variable.getName()));
                    }
                }
                else {
                    if (function != null && Objects.equals(token.sequence, function.getName()))
                        infixExpression.add(domain.getFunction(function.getName()));
                    else
                        infixExpression.add(domain.getFunctionalVariable(function.getName(), token.sequence));
                }
            }
            else if (domain.isOperator(token.sequence)) {
                infixExpression.add(domain.getOperator(token.sequence));                                    // Operator
                if (Objects.equals(token.sequence, ASSIGNMENT))
                    inFunctionAssignment = true;
            }
            else if (domain.isFunction(token.sequence)) {
                if (function == null && !Objects.equals(token.sequence, FUNC))                              // Function
                    throw new ParserException("The function already exists, use another name.");

                infixExpression.add(domain.getFunction(token.sequence));
            }
            else
                infixExpression.add(Literal.parseLiteral(token));                                           // Constant
        }

        return infixExpression;
    }

}
