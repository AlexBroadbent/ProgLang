package framework;

import eval.Expression;
import eval.ExpressionException;
import eval.IncomparableTypeException;
import eval.NoValueException;
import gui.XLogger;
import lexer.UnknownSequenceException;
import parser.ParserException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

/**
 * x++.framework
 *
 * @author Alexander Broadbent
 * @version 29/12/2015
 */
public abstract class ExpressionTest extends BaseTest {

    private static final String MSG_ASSERT_TYPE   = "Test::%s - Asserting result type [%s] equals expected type [%s]";
    private static final String MSG_ASSERT_RESULT = "Result is %s, expected %s";


    /*
            Testing methods
     */
    protected void runExpressionTest(String input, Object expResult) throws UnknownSequenceException,
            ParserException, ExpressionException, IncomparableTypeException, NoValueException {
        runExpressionTest(null, input, expResult);
    }

    protected void runExpressionTest(String msg, String input, Object expResult) throws UnknownSequenceException,
            ParserException, ExpressionException, IncomparableTypeException, NoValueException {
        Object result = getResultFromInput(input);

        assertTypeOfResult(result, expResult.getClass());
        if (msg != null)
            assertResult(msg, expResult, result);
        else
            assertResult(expResult, result);
    }

    protected void runVariableTest(String input, String varName, Object expResult) throws ExpressionException,
            IncomparableTypeException, ParserException, UnknownSequenceException, NoValueException {
        getResultFromInput(input);
        verifyVariable(varName, expResult);
    }

    protected void runExceptionTest(String input, Class<? extends Exception> expException) {
        try {
            getResultFromInput(input);
            fail("No exception was thrown from the input " + input);
        }
        catch (Exception ex) {
            assertTypeOfResult(ex, expException);
        }
    }


    /*
            Helper methods
     */

    protected void setDomainVariable(String name, Object value) {
        domain.getOrCreateVariable(name).setValue(value);
    }

    private void verifyVariable(String varName, Object expResult) {
        Object result;

        try {
            result = getValueFromDomainVariable(varName);
        }
        catch (NoValueException ex) {
            result = null;
        }

        if (expResult != null)
            assertTypeOfResult(result, expResult.getClass());
        assertResult(expResult, result);
    }

    protected Object getResultFromInput(String input) throws ExpressionException, IncomparableTypeException,
            ParserException, UnknownSequenceException, NoValueException {
        Expression expression = getExpressionFromInput(input);
        return getValueFromExpression(expression);
    }

    protected Expression getExpressionFromInput(String input) throws ExpressionException,
            UnknownSequenceException, ParserException {
        return new Expression(domain, domain.getLexer().readAllTokens(input));
    }

    protected Object getValueFromExpression(Expression expression) throws ExpressionException,
            UnknownSequenceException, IncomparableTypeException, NoValueException {
        return expression.execute();
    }

    private Object getValueFromDomainVariable(String variableName) throws NoValueException {
        return domain.getVariable(variableName).getValue();
    }

    protected boolean hasVariableBeenCreated(String varName) {
        return domain.hasVariable(varName);
    }


    /*
            Assert Methods
     */

    protected void assertTypeOfResult(Object result, Class expectedClass) {
        XLogger.log(String.format(MSG_ASSERT_TYPE, getClass().getSimpleName(), result.getClass().getSimpleName(),
                expectedClass.getSimpleName()));
        assertThat(String.format(MSG_ASSERT_RESULT, result.getClass().getSimpleName(), expectedClass.getSimpleName()),
                result, instanceOf(expectedClass));
    }

    <T> void assertResult(T expected, T actual) {
        assertResult(getClass().getSimpleName(), expected, actual);
    }

    protected void assertResultRange(Double result, Double min, Double max) {
        String log = String.format(MSG_ASSERT_RESULT_LOG, getClass().getSimpleName(), result,
                min + "<=" + result + "<=" + max);
        XLogger.log(log);
        assertTrue(log, result >= min && result <= max);
    }

    private <T> void assertResult(String testName, T expected, T actual) {
        XLogger.log(String.format(MSG_ASSERT_RESULT_LOG, testName, actual, expected));
        assertTrue(String.format(MSG_ASSERT_RESULT, actual, expected), expected.equals(actual));
    }

}
