package framework;

import eval.*;
import gui.XLogger;
import lexer.UnknownSequenceException;
import model.Domain;
import org.junit.After;
import org.junit.Before;
import parser.ParserException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

/**
 * x++.framework
 *
 * @author Alexander Broadbent
 * @version 29/12/2015
 */
public class ExpressionTest extends BaseTest {

    private static final String MSG_ASSERT_TYPE   = "Result is of type %s, expected %s";
    private static final String MSG_ASSERT_RESULT = "Result is %s, expected %s";

    private static Literal wrap(Object object) {
        return Domain.wrapLiteral(object);
    }

    @Before
    public void setUp() {
        Domain.resetInstance();
    }

    @After
    public void tearDown() {
        Domain.resetInstance();
    }

    /*
            Testing methods
     */
    protected void runExpressionTest(String input, Object expResult) throws UnknownSequenceException,
            ParserException, ExpressionException, IncomparableTypeException {
        runExpressionTest(null, input, expResult);
    }

    protected void runExpressionTest(String msg, String input, Object expResult) throws UnknownSequenceException,
            ParserException, ExpressionException, IncomparableTypeException {
        Object result = getResultFromInput(input);

        assertTypeOfResult(result, expResult.getClass());
        if (msg != null)
            assertResult(msg, expResult, result);
        else
            assertResult(expResult, result);
    }

    protected void runVariableTest(String input, String varName, Object expResult) throws ExpressionException,
            IncomparableTypeException, ParserException, UnknownSequenceException {
        getResultFromInput(input);
        verifyVariable(varName, expResult);
    }

    /*
            Helper methods
     */

    protected void runExceptionTest(String input, Class<? extends Exception> expException) {
        boolean result = assertExceptionIsThrown(input, expException);
        assertResult(getClass().getSimpleName() + " - Asserting " + expException.getSimpleName() + " was thrown", true, result);
    }

    @SuppressWarnings( "unchecked" )  // Catch is in place to check a casting exception
    protected void runListTest(String input, XList expResult) throws UnknownSequenceException,
            ParserException, ExpressionException, IncomparableTypeException {
        Expression expression = getExpressionFromInput(input);
        Object list = getValueFromExpression(expression);
        XList result = null;

        try {
            result = (XList) list;
        }
        catch (ClassCastException ex) {
            fail("A List was not returned, instead result is of type " + getValueFromExpression(expression).getClass().getSimpleName());
        }

        assertResult(getClass().getSimpleName() + " - Assert size of result list matches expected size", expResult.size(), result.size());
        for (int i = 0; i < result.size(); i++)
            assertResult(getClass().getSimpleName() + " - list position: " + i, expResult.get(i).getValue(), result.get(i).getValue());
    }

    private void verifyVariable(String varName, Object expResult) {
        Object result = getValueFromDomainVariable(varName);

        if (expResult != null)
            assertTypeOfResult(result, expResult.getClass());
        assertResult(expResult, result);
    }

    protected void setDomainVariable(String name, Object value) {
        Domain.getInstance().getOrCreateVariable(name).setValue(value);
    }

    protected Expression getExpressionFromInput(String input) throws ExpressionException,
            UnknownSequenceException, ParserException {
        Domain model = Domain.getInstance();
        return new Expression(model, model.getLexer().readAllTokens(input));
    }

    protected Object getValueFromExpression(Expression expression) throws ExpressionException,
            UnknownSequenceException, IncomparableTypeException {
        return expression.execute();
    }

    private Object getValueFromDomainVariable(String variableName) {
        return Domain.getInstance().getVariable(variableName).getValue();
    }

    private Object getResultFromInput(String input) throws ExpressionException, IncomparableTypeException,
            ParserException, UnknownSequenceException {
        Expression expression = getExpressionFromInput(input);
        return expression.execute();
    }

    protected boolean hasVariableBeenCreated(String varName) {
        return Domain.getInstance().hasVariable(varName);
    }





    /*
            Assert Methods
     */

    private boolean assertExceptionIsThrown(String input, Class<? extends Exception> expectedException) {
        try {
            Object result = getValueFromExpression(getExpressionFromInput(input));
            return result == null;
        }
        catch (Exception ex) {
            return (ex.getClass().equals(expectedException));
        }
    }

    protected void assertTypeOfResult(Object result, Class expectedClass) {
        assertThat(String.format(MSG_ASSERT_TYPE, result.getClass().getSimpleName(),
                expectedClass.getSimpleName()), result, instanceOf(expectedClass));
    }

    <T> void assertResult(T expected, T actual) {
        assertResult(getClass().getSimpleName(), expected, actual);
    }

    private <T> void assertResult(String testName, T expected, T actual) {
        XLogger.log(String.format(MSG_ASSERT_RESULT_LOG, testName, actual, expected));
        assertTrue(String.format(MSG_ASSERT_RESULT, actual, expected), expected.equals(actual));
    }

}
