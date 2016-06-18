package eval;

import gui.XLogger;
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

    private static final String MSG_SET_NO_VALUE = "The assigned literal has no value";


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
        if (token.token == Token.TEXT)
            return Domain.wrapLiteral(token.sequence.substring(1, token.sequence.length() - 1));
        if (token.token == Token.BINARY_8BIT)
            return Domain.wrapLiteral(Integer.parseUnsignedInt(token.sequence, 2));

        return Domain.wrapLiteral(token.sequence);
    }

    public Object getValue() throws NoValueException {
        return value;
    }

    public void setValue(Object value) {
        if (value instanceof Literal) {
            try {
                this.value = ((Literal) value).getValue();
            }
            catch (NoValueException e) {
                XLogger.warning(MSG_SET_NO_VALUE);
            }
        }
        else
            this.value = value;
    }

    @Override
    public Literal evaluate(Domain domain, Stack<Literal> stack) {
        return evaluate(domain, stack, false);
    }

    @Override
    public Literal evaluate(Domain domain, Stack stack, boolean returnExpression) {
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
        try {
            return getValue().toString();
        }
        catch (NoValueException e) {
            return "null";
        }
    }

}
