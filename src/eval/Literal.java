package eval;

import lexer.Token;
import model.Domain;
import operator.IOperator;

import java.util.List;
import java.util.Stack;

import static parser.IConstants.NULL;

/**
 * LazyLanguage.eval
 *
 * @version     01/12/2015
 * @author      Alexander Broadbent
 */
public class Literal implements ICalculable {

    public static Literal parseLiteral(Token token) {
        if (token.token == Token.BOOLEAN)
            return new Literal(Boolean.valueOf(token.sequence));
        if (token.token == Token.NUMBER)
            return new Literal(Integer.valueOf(token.sequence));
        if (token.token == Token.DECIMAL)
            return new Literal(Double.valueOf(token.sequence));
        if (token.token == Token.BINARY_8BIT)
            return new Literal(Integer.parseUnsignedInt(token.sequence, 2));
        if (token.token == Token.LIST)
            return new Literal(LinkList.parse(token));
        if (token.sequence.equalsIgnoreCase(NULL))
            return new Literal(null);

        return new Literal(token.sequence);
    }

    protected Object value;


    public Literal(Object value) {
        this.value = value;
    }


    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public Literal evaluate(Domain domain, Stack stack) {
        return this;
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
        return getValue().toString();
    }

}
