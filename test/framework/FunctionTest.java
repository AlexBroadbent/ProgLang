package framework;

import eval.Expression;
import eval.Literal;
import lexer.UnknownSequenceException;
import model.Domain;
import parser.ExpressionException;
import parser.IncomparableTypeException;
import parser.ParserException;

/**
 * x++.framework
 *
 * @author Alexander Broadbent
 * @version 22/03/2016
 */
public class FunctionTest extends ExpressionTest {

    protected static Literal wrap(Object object) {
        return Domain.wrapLiteral(object);
    }

    protected void runFunctionTest(String decInput, String funcName, String runInput, Object runResult)
            throws UnknownSequenceException, ParserException, ExpressionException, IncomparableTypeException {
        // Create function
        Expression declaration = getExpressionFromInput(decInput);
        getValueFromExpression(declaration); // Returns null
        assertFunctionCreated(funcName);

        // Run function
        Expression run = getExpressionFromInput(runInput);
        Object result = getValueFromExpression(run);
        assertResult(runResult, result);
    }

    private void assertFunctionCreated(String functionName) {
        Domain model = Domain.getInstance();
        assertResult(true, model.getFunctionList().contains(functionName));
    }


}
