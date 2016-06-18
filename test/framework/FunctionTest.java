package framework;

import eval.Expression;
import eval.ExpressionException;
import eval.IncomparableTypeException;
import eval.NoValueException;
import lexer.UnknownSequenceException;
import parser.ParserException;

import static org.junit.Assert.assertTrue;

/**
 * x++.framework
 *
 * @author Alexander Broadbent
 * @version 22/03/2016
 */
public abstract class FunctionTest extends ExpressionTest {

    protected void runFunctionTest(String decInput, String funcName, String runInput, Object runResult)
            throws UnknownSequenceException, ParserException, ExpressionException, IncomparableTypeException, NoValueException {
        runFunctionDeclaration(decInput, funcName);
        runFunction(runInput, runResult);
    }

    protected void runFunctionDeclaration(String input, String functionName)
            throws UnknownSequenceException, IncomparableTypeException, ExpressionException, ParserException, NoValueException {
        Expression declaration = getExpressionFromInput(input);
        getValueFromExpression(declaration);
        assertFunctionCreated(functionName);
    }


    /*
     *      Helper Functions
     */
    private void assertFunctionCreated(String functionName) {
        assertTrue(domain.getFunctionList().contains(functionName));
    }

    private void runFunction(String input, Object expResult)
            throws UnknownSequenceException, ParserException, ExpressionException, IncomparableTypeException, NoValueException {
        Expression run = getExpressionFromInput(input);
        Object result = getValueFromExpression(run);
        assertTypeOfResult(result, expResult.getClass());
        assertResult(expResult, result);
    }

}