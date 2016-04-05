package operator.loop;

import eval.ICalculable;
import eval.Literal;
import model.Domain;
import operator.IConstants;
import operator.IOperator;
import operator.IPrecedence;
import operator.base.UnaryOperator;
import parser.ExpressionException;
import parser.IncomparableTypeException;

import java.util.List;
import java.util.Stack;

/**
 * ProgLang.operator.loop
 *
 * @author Alexander Broadbent
 * @version 27/03/2016
 */
public class Do extends UnaryOperator {

    @Override
    public String getToken() {
        return IConstants.DO;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.LOOP;
    }

    @Override
    public Literal evaluate(Domain domain, Stack<Literal> stack, boolean returnExpression)
            throws IncomparableTypeException, ExpressionException {
        return Domain.wrapLiteral(this);    // TODO: Take this out, was crap fix for loop evaluation
    }

    @Override
    public void toPostFix(List<ICalculable> infix, int infixIndex, List<ICalculable> postfix, Stack<IOperator> operatorStack) {
        postfix.add(this);
    }

}
