package framework;

import eval.Expression;
import gui.XLogger;
import lexer.UnknownSequenceException;
import model.Domain;
import org.junit.After;
import org.junit.Before;
import parser.ExpressionException;
import parser.IncomparableTypeException;
import parser.ParserException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * x++.framework
 *
 * @author Alexander Broadbent
 * @version 29/12/2015
 */
public class ExpressionTest {

    protected static final String MSG_ASSERT_TYPE = "Result is of type %s, expected %s";
    protected static final String MSG_ASSERT_RESULT = "Result is %s, expected %s";

    @Before
    public void setUp() {
        Domain.invalidateInstance();
    }

    @After
    public void tearDown() {
        Domain.invalidateInstance();
    }


    /*
            Testing methods
     */
    protected void runExpressionTest(String input, Object expResult) throws UnknownSequenceException, ParserException, ExpressionException, IncomparableTypeException {
        runExpressionTest(null, input, expResult);
    }

    protected void runExpressionTest(String msg, String input, Object expResult) throws UnknownSequenceException, ParserException, ExpressionException, IncomparableTypeException {
        Expression expression = getExpressionFromInput(input);
        Object result = getValueFromExpression(expression);

        assertTypeOfResult(result, expResult.getClass());
        if (msg != null)
            assertResult(msg, expResult, result);
        else
            assertResult(expResult, result);
    }

    protected void runExpressionVariableTest(String input, String varName, Object expResult) throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        Expression expression = getExpressionFromInput(input);
        expression.execute();
        Object result = getValueFromDomainVariable(varName);

        assertTypeOfResult(result, expResult.getClass());
        assertResult(expResult, result);
    }

    protected void runExpressionExceptionTest(String input, Class<? extends Exception> expException) {
        Object result = assertExceptionIsThrown(input, expException);
        assertResult(Boolean.TRUE, result);
    }





    /*
            Helper methods
     */

    protected void setDomainVariable(String name, Object value) {
        Domain.getInstance().getOrCreateVariable(name).setValue(value);
    }


    protected Expression getExpressionFromInput(String input) throws ExpressionException, UnknownSequenceException, ParserException {
        Domain model = Domain.getInstance();
        return new Expression(model, model.getLexer().readAllTokens(input));
    }

    protected Object getValueFromExpression(Expression expression) throws ExpressionException, UnknownSequenceException, IncomparableTypeException {
        return expression.execute();
    }

    protected Object getValueFromDomainVariable(String variableName) {
        return Domain.getInstance().getVariable(variableName).getValue();
    }


    protected Object getResultFromInput(String input) throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        Expression expression = getExpressionFromInput(input);
        return expression.execute();
    }







    /*
            Assert Methods
     */

    protected boolean assertErrorInExpression(String input) {
        try {
            getExpressionFromInput(input);
            return false;
        }
        catch (UnknownSequenceException | ExpressionException | ParserException e) {
            return true;
        }
    }

    protected boolean assertExceptionIsThrown(String input, Class<? extends Exception> expectedException) {
        try {
            Object result = getValueFromExpression(getExpressionFromInput(input));
            return result == null;
        }
        catch (Exception ex) {
            return (ex.getClass().equals(expectedException));
        }
    }

    protected void assertTypeOfResult(Object result, Class expectedClass) {
        assertThat(String.format(MSG_ASSERT_TYPE, result.getClass().getSimpleName(), expectedClass.getSimpleName()),
                result, instanceOf(expectedClass));
    }

    protected <T> void assertResult(T expected, T actual) {
        assertResult(getClass().getSimpleName(), expected, actual);
    }

    protected <T> void assertResult(String testName, T expected, T actual) {
        XLogger.log("Test::" + testName + " - Asserting result " + actual + " equals expected " + expected);
        assertTrue(String.format(MSG_ASSERT_RESULT, actual, expected), expected.equals(actual));
    }

}
