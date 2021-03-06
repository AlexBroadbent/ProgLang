package type;

import uk.co.alexbroadbent.eval.ExpressionException;
import uk.co.alexbroadbent.eval.IncomparableTypeException;
import framework.ExpressionTest;
import uk.co.alexbroadbent.lexer.UnknownSequenceException;
import org.junit.Test;
import uk.co.alexbroadbent.parser.ParserException;

/**
 * x++.type
 * <p>
 * Tests the IncomparableTypeException is thrown when executing lambda expressions that have
 * incorrect value types.
 *
 * @author Alexander Broadbent
 * @version 05/01/2016
 */
public class IncomparableTypeTest extends ExpressionTest {

    private static final String                     INPUT_MATH = "true >> 2";
    private static final String                     INPUT_EQU  = "'hello' == 2";
    private static final String                     INPUT_NOT  = "!'hello'";
    private static final Class<? extends Exception> CLASS_MATH = IncomparableTypeException.class;
    private static final Boolean                    RESULT_EQU = Boolean.FALSE;
    private static final Class<? extends Exception> CLASS_NOT  = IncomparableTypeException.class;


    @Test
    public void shiftOperatorIncomparableTest() {
        runExceptionTest(INPUT_MATH, CLASS_MATH);
    }

    @Test
    public void equalityOperatorExceptionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_EQU, RESULT_EQU);
    }

    @Test
    public void equalityOperatorTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExceptionTest(INPUT_NOT, CLASS_NOT);
    }

}
