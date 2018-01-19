package framework;

import uk.co.alexbroadbent.eval.Expression;
import uk.co.alexbroadbent.eval.ExpressionException;
import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.lexer.UnknownSequenceException;
import uk.co.alexbroadbent.model.Domain;
import uk.co.alexbroadbent.parser.ParserException;

/**
 * x++.framework
 *
 * @author Alexander Broadbent
 * @version 22/03/2016
 */
public abstract class FunctionTest extends ExpressionTest {

    protected void runFunctionTest(String decInput, String funcName, String runInput, Object runResult)
            throws UnknownSequenceException, ParserException, ExpressionException, IncomparableTypeException {
        runFunctionDeclaration(decInput, funcName);
        runFunction(runInput, runResult);
    }

    protected void runFunctionDeclaration(String input, String functionName)
            throws UnknownSequenceException, IncomparableTypeException, ExpressionException, ParserException {
        Expression declaration = getExpressionFromInput(input);
        getValueFromExpression(declaration);
        assertFunctionCreated(functionName);
    }


    /*
     *      Helper Functions
     */
    private void assertFunctionCreated(String functionName) {
        Domain model = Domain.getInstance();
        assertResult(true, model.getFunctionList().contains(functionName));
    }

    private void runFunction(String input, Object expResult)
            throws UnknownSequenceException, ParserException, ExpressionException, IncomparableTypeException {
        Expression run = getExpressionFromInput(input);
        Object result = getValueFromExpression(run);
        assertTypeOfResult(result, expResult.getClass());
        assertResult(expResult, result);
    }

}