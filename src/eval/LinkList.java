package eval;

import com.google.common.collect.Lists;
import lexer.Token;
import lexer.UnknownSequenceException;
import model.Domain;
import operator.IOperator;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Stack;

import static lexer.IToken.*;

/**
 * ProgLang.eval
 *
 * @author Alexander Broadbent
 * @version 26/02/2016
 */
public class LinkList extends Literal {

    public static List<Literal> parse(Token token) {
        List<Token> tokens = Lists.newLinkedList();
        try {
            // TODO: find a better solution to read tokens
            tokens = Domain.getInstance().getLexer().readAllTokens(token.sequence, true);
        }
        catch (UnknownSequenceException e) {
            e.printStackTrace();
        }

        List<Literal> list = Lists.newLinkedList();
        for (Token t : tokens)
            if (t.token != LIST_START && t.token != LIST_END && t.token != LIST_SEPARATOR)
                list.add(Literal.parseLiteral(t));

        return list;
    }


    public LinkList() {
        super(Lists.newLinkedList());
    }


    @Override
    public Literal evaluate(Domain domain, Stack stack) {
        return new Literal(this.toString()); //TODO: FIX THIS!
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        postfix.add(this);
    }

    @Override
    public int getType() {
        return ICalculableType.LITERAL;
    }

    @Override
    public String toString() {
        return StringUtils.join(((List<Literal>) getValue()), " -> ");
    }

}
