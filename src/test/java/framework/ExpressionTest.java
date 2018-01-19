package framework;

import uk.co.alexbroadbent.eval.Expression;
import uk.co.alexbroadbent.eval.ExpressionException;
import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.ui.XLogger;
import uk.co.alexbroadbent.lexer.UnknownSequenceException;
import uk.co.alexbroadbent.model.Domain;
import org.junit.After;
import org.junit.Before;
import uk.co.alexbroadbent.parser.ParserException;

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
        Domain.getInstance().getOrCreateVariable(name).setValue(value);
    }

    private void verifyVariable(String varName, Object expResult) {
        Object result = getValueFromDomainVariable(varName);

        if (expResult != null)
            assertTypeOfResult(result, expResult.getClass());
        assertResult(expResult, result);
    }

    protected Object getResultFromInput(String input) throws ExpressionException, IncomparableTypeException,
            ParserException, UnknownSequenceException {
        Expression expression = getExpressionFromInput(input);
        return getValueFromExpression(expression);
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

    protected boolean hasVariableBeenCreated(String varName) {
        return Domain.getInstance().hasVariable(varName);
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
