package parser;

import com.google.common.collect.Lists;
import eval.ICalculable;
import eval.Literal;
import lexer.Token;
import model.Domain;
import operator.IConstants;

import java.util.List;
import java.util.Objects;

import static lexer.IToken.VARIABLE;
import static operator.IConstants.ASSIGNMENT;

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
        boolean inFunctionDeclaration = false;
        String functionName = null;
        boolean inFunctionAssignment = false;

        for (Token token : tokens) {
            if (token.token == VARIABLE) {
                // If in a declaration then add variables to the functional domain                          // Variable
                if (inFunctionDeclaration && !inFunctionAssignment)
                    if (functionName == null) {
                        functionName = token.sequence;
                        infixExpression.add(Domain.wrapLiteral(token.sequence));
                    }
                    else
                        infixExpression.add(domain.addFunctionalVariable(functionName, token.sequence));
                else if (inFunctionAssignment)
                    infixExpression.add(domain.getFunctionalVariable(functionName, token.sequence));
                else
                    infixExpression.add(domain.getOrCreateVariable(token.sequence));
            }
            else if (domain.isOperator(token.sequence)) {
                infixExpression.add(domain.getOperator(token.sequence));                                    // Operator
                if (inFunctionDeclaration && Objects.equals(token.sequence, ASSIGNMENT))
                    inFunctionAssignment = true;
            }
            else if (domain.isFunction(token.sequence)) {
                // Check for functions being created that already exist                                     // Function
                if (inFunctionDeclaration && functionName == null)
                    throw new ParserException("The function already exists, use another name.");

                infixExpression.add(domain.getFunction(token.sequence));
                if (Objects.equals(token.sequence, IConstants.FUNC))
                    inFunctionDeclaration = true;
            }
            else
                infixExpression.add(Literal.parseLiteral(token));                                           // Constant
        }

        return infixExpression;
    }

}
