package parser;

import com.google.common.collect.Lists;
import eval.ICalculable;
import eval.LinkList;
import eval.Literal;
import lexer.Token;
import model.Domain;

import java.util.List;

import static lexer.IToken.VARIABLE;

/**
 * LazyLanguage.parser
 *
 * @version     01/12/2015
 * @author      Alexander Broadbent
 */
public class Parser implements IParser {

    @Override
    public List<ICalculable> parse(Domain domain, List<Token> tokens) {
        List<ICalculable> infixExpression = Lists.newArrayList();

        //List<Token> listTokens = Lists.newLinkedList();
        //boolean inList = false;

        for (Token token : tokens) {
          /*  if (inList) {
                if (token.token == LIST_END) {
                    infixExpression.add(parseList(listTokens));
                    inList = false;
                }
                else
                    listTokens.add(token);

                continue;
            }
*/
            if (token.token == VARIABLE)
                infixExpression.add(domain.getOrCreateVariable(token.sequence));            // Variable
            else if (domain.isOperator(token.sequence))
                infixExpression.add(domain.getOperator(token.sequence));                    // Operator
  //          else if (token.token == LIST_START)
    //            inList = true;                                                              // List
            else
                infixExpression.add(Literal.parseLiteral(token));                           // Constant
        }

        return infixExpression;
    }

    protected ICalculable parseList(List<Token> tokens) {
        LinkList list = new LinkList();

        //for (Token token : tokens)
            //if (token.token != LIST_SEPARATOR)
                //list.add(Literal.parseLiteral(token));

        return list;
    }

}
