package eval;

import lexer.Token;
import model.Domain;
import operator.IOperator;

import java.util.List;
import java.util.Stack;

/**
 * x++.eval
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class Literal implements ICalculable {

    protected Object value;

    public Literal(Object value) {
        this.value = value;
    }

    public static Literal parseLiteral(Token token) {
        if (token.token == Token.BOOLEAN)
            return Domain.wrapLiteral(Boolean.valueOf(token.sequence));
        if (token.token == Token.NUMBER)
            return Domain.wrapLiteral(Integer.valueOf(token.sequence));
        if (token.token == Token.DECIMAL)
            return Domain.wrapLiteral(Double.valueOf(token.sequence));
        if (token.token == Token.BINARY_8BIT)
            return Domain.wrapLiteral(Integer.parseUnsignedInt(token.sequence, 2));

        return Domain.wrapLiteral(token.sequence);
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public Literal evaluate(Domain domain, Stack stack, boolean funcDec) {
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
        return getValue() != null ? getValue().toString() : "null";
    }

}
