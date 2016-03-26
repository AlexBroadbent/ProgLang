package framework;

import eval.Expression;
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

    protected void runFunctionTest(String decInput, String funcName, String runInput, Object runResult)
            throws UnknownSequenceException, ParserException, ExpressionException, IncomparableTypeException {
        runFunctionDeclaration(decInput, funcName);
        runFunction(runInput, runResult);
    }

    private void assertFunctionCreated(String functionName) {
        Domain model = Domain.getInstance();
        assertResult(true, model.getFunctionList().contains(functionName));
    }




    /*
     *      Helper Functions
     */

    protected void runFunctionDeclaration(String input, String functionName)
            throws UnknownSequenceException, IncomparableTypeException, ExpressionException, ParserException {
        Expression declaration = getExpressionFromInput(input);
        getValueFromExpression(declaration);
        assertFunctionCreated(functionName);
    }

    private void runFunction(String input, Object expResult)
            throws UnknownSequenceException, ParserException, ExpressionException, IncomparableTypeException {
        Expression run = getExpressionFromInput(input);
        Object result = getValueFromExpression(run);
        assertResult(expResult, result);
    }

}
