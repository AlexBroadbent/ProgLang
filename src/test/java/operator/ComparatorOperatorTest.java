package operator;

import eval.ExpressionException;
import eval.IncomparableTypeException;
import framework.ExpressionTest;
import lexer.UnknownSequenceException;
import org.junit.Test;
import parser.ParserException;

/**
 * x++.operator
 *
 * @author Alexander Broadbent
 * @version 24/03/2016
 */
public class ComparatorOperatorTest extends ExpressionTest {

    private static final String  VAR_X       = "x";
    private static final String  VAR_Y       = "y";
    private static final Integer VAR_X_VALUE = 3;
    private static final Integer VAR_Y_VALUE = 5;

    private static final String                     INPUT_GT           = "x > y";
    private static final String                     INPUT_GT_ERR       = "x > true";
    private static final String                     INPUT_GT_EQ        = "x >= x";
    private static final String                     INPUT_GT_EQ_ERR    = "x >= 'test'";
    private static final String                     INPUT_LT           = "x < y";
    private static final String                     INPUT_LT_ERR       = "x < false";
    private static final String                     INPUT_LT_EQ        = "x <= y";
    private static final String                     INPUT_LT_EQ_ERR    = "x <= 'hello'";
    private static final Boolean                    RESULT_GT          = Boolean.FALSE;
    private static final Boolean                    RESULT_GT_EQ       = Boolean.TRUE;
    private static final Boolean                    RESULT_LT          = Boolean.TRUE;
    private static final Boolean                    RESULT_LT_EQ       = Boolean.TRUE;
    private static final Class<? extends Exception> CLASS_INCOMPARABLE = IncomparableTypeException.class;


    @Override
    public void setUp() {
        super.setUp();

        setDomainVariable(VAR_X, VAR_X_VALUE);
        setDomainVariable(VAR_Y, VAR_Y_VALUE);
    }


    @Test
    public void greaterThanOperatorTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_GT, RESULT_GT);
    }

    @Test
    public void greaterThanEqualOperatorTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_GT_EQ, RESULT_GT_EQ);
    }

    @Test
    public void lessThanOperatorTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_LT, RESULT_LT);
    }

    @Test
    public void lessThanEqualOperatorTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_LT_EQ, RESULT_LT_EQ);
    }

    @Test
    public void greaterThanOperatorExceptionTest() {
        runExceptionTest(INPUT_GT_ERR, CLASS_INCOMPARABLE);
    }

    @Test
    public void greaterThanEqualOperatorExceptionTest() {
        runExceptionTest(INPUT_GT_EQ_ERR, CLASS_INCOMPARABLE);
    }

    @Test
    public void lessThanOperatorExceptionTest() {
        runExceptionTest(INPUT_LT_ERR, CLASS_INCOMPARABLE);
    }

    @Test
    public void lessThanEqualOperatorExceptionTest() {
        runExceptionTest(INPUT_LT_EQ_ERR, CLASS_INCOMPARABLE);
    }

}
