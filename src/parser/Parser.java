package parser;

import com.google.common.collect.Lists;
import eval.ICalculable;
import eval.Literal;
import lexer.Token;
import model.Domain;

import java.util.List;

import static lexer.IToken.VARIABLE;

/**
 * LazyLanguage.parser
 *
 * @author      Alexander Broadbent
 * @version     01/12/2015
 */
public class Parser implements IParser {

    @Override
    public List<ICalculable> parse(Domain domain, List<Token> tokens) {
        List<ICalculable> infixExpression = Lists.newArrayList();

        for (Token token : tokens) {
            if (token.token == VARIABLE)
                infixExpression.add(domain.getOrCreateVariable(token.sequence));            // Variable
            else if (domain.isOperator(token.sequence))
                infixExpression.add(domain.getOperator(token.sequence));                    // Operator
            else if (domain.isFunction(token.sequence))
                infixExpression.add(domain.getFunction(token.sequence));                    // Function
            else
                infixExpression.add(Literal.parseLiteral(token));                           // Constant
        }

        return infixExpression;
    }

}
