package operator;

import framework.ExpressionTest;
import lexer.UnknownSequenceException;
import org.junit.Test;
import parser.ExpressionException;
import parser.IncomparableTypeException;
import parser.ParserException;

/**
 * x++.operator
 *
 * @author Alexander Broadbent
 * @version 04/01/2016
 */
public class MathOperatorUT extends ExpressionTest {

    private static final String                     INPUT_SIN      = "sin(1)";
    private static final String                     INPUT_SIN_ERR  = "sin(true)";
    private static final String                     INPUT_COS      = "cos(0.5)";
    private static final String                     INPUT_COS_ERR  = "cos(false)";
    private static final String                     INPUT_TAN      = "tan(1.5)";
    private static final String                     INPUT_TAN_ERR  = "tan(\"s5\")";
    private static final String                     INPUT_ASIN     = "asin(1)";
    private static final String                     INPUT_ASIN_ERR = "asin(false)";
    private static final String                     INPUT_ACOS     = "acos(.5)";
    private static final String                     INPUT_ACOS_ERR = "acos(true)";
    private static final String                     INPUT_ATAN     = "atan(1.5)";
    private static final String                     INPUT_ATAN_ERR = "atan(true)";
    private static final String                     INPUT_POW      = "2^4";
    private static final String                     INPUT_POW_ERR  = "2^true";
    private static final String                     INPUT_SQR      = "sqrt(9)";
    private static final String                     INPUT_SQR_ERR  = "sqrt('false')";
    private static final String                     INPUT_NLN      = "ln(4)";
    private static final String                     INPUT_NLN_ERR  = "ln(false)";
    private static final String                     INPUT_L10      = "log10(2)";
    private static final String                     INPUT_L10_ERR  = "log10(true)";
    private static final String                     INPUT_EXP      = "exp(4)";
    private static final String                     INPUT_EXP_ERR  = "exp(false)";
    private static final String                     INPUT_MOD      = "4%2";
    private static final String                     INPUT_MOD_ERR  = "4%false";
    private static final String                     INPUT_ALL      = "sin(1)+cos(.5)-tan(1.5)*asin(1)*(2^4)/sqrt(9)-ln(4)+log10(2)/exp(4)*(4%2)";
    private static final Double                     RESULT_SIN     = Math.sin(1);
    private static final Double                     RESULT_COS     = Math.cos(0.5);
    private static final Double                     RESULT_TAN     = Math.tan(1.5);
    private static final Double                     RESULT_ASIN    = Math.asin(1);
    private static final Double                     RESULT_ACOS    = Math.acos(0.5);
    private static final Double                     RESULT_ATAN    = Math.atan(1.5);
    private static final Double                     RESULT_POW     = Math.pow(2, 4);
    private static final Double                     RESULT_SQR     = Math.sqrt(9);
    private static final Double                     RESULT_NLN     = Math.log(4);
    private static final Double                     RESULT_L10     = Math.log10(2);
    private static final Double                     RESULT_EXP     = Math.exp(4);
    private static final Double                     RESULT_MOD     = (double) (4 % 2);
    private static final Double                     RESULT_ALL     = RESULT_SIN + RESULT_COS - RESULT_TAN * RESULT_ASIN *
            RESULT_POW / RESULT_SQR - RESULT_NLN + RESULT_L10 / RESULT_EXP * RESULT_MOD;
    private static final Class<? extends Exception> CLASS_ITE      = IncomparableTypeException.class;


    @Test
    public void operatorSineTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_SIN, RESULT_SIN);
    }

    @Test
    public void operatorCosineTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_COS, RESULT_COS);
    }

    @Test
    public void operatorTangentTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_TAN, RESULT_TAN);
    }

    @Test
    public void operatorASineTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_ASIN, RESULT_ASIN);
    }

    @Test
    public void operatorACosineTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_ACOS, RESULT_ACOS);
    }

    @Test
    public void operatorATangentTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_ATAN, RESULT_ATAN);
    }

    @Test
    public void operatorPowerTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_POW, RESULT_POW);
    }

    @Test
    public void operatorSquareRootTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_SQR, RESULT_SQR);
    }

    @Test
    public void operatorNaturalLogTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_NLN, RESULT_NLN);
    }

    @Test
    public void operatorLog10Test()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_L10, RESULT_L10);
    }

    @Test
    public void operatorExponentialTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_EXP, RESULT_EXP);
    }

    @Test
    public void operatorModuloTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_MOD, RESULT_MOD);
    }

    @Test
    public void operatorAllPrecedenceTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_ALL, RESULT_ALL);
    }

    @Test
    public void operatorSineExceptionTest() {
        runExceptionTest(INPUT_SIN_ERR, CLASS_ITE);
    }

    @Test
    public void operatorCosineExceptionTest() {
        runExceptionTest(INPUT_COS_ERR, CLASS_ITE);
    }

    @Test
    public void operatorTangentExceptionTest() {
        runExceptionTest(INPUT_TAN_ERR, CLASS_ITE);
    }

    @Test
    public void operatorASineExceptionTest() {
        runExceptionTest(INPUT_ASIN_ERR, CLASS_ITE);
    }

    @Test
    public void operatorACosineExceptionTest() {
        runExceptionTest(INPUT_ACOS_ERR, CLASS_ITE);
    }

    @Test
    public void operatorATangentExceptionTest() {
        runExceptionTest(INPUT_ATAN_ERR, CLASS_ITE);
    }

    @Test
    public void operatorPowerExceptionTest() {
        runExceptionTest(INPUT_POW_ERR, CLASS_ITE);
    }

    @Test
    public void operatorSquareRootExceptionTest() {
        runExceptionTest(INPUT_SQR_ERR, CLASS_ITE);
    }

    @Test
    public void operatorNaturalLogExceptionTest() {
        runExceptionTest(INPUT_NLN_ERR, CLASS_ITE);
    }

    @Test
    public void operatorLog10ExceptionTest() {
        runExceptionTest(INPUT_L10_ERR, CLASS_ITE);
    }

    @Test
    public void operatorExponentialExceptionTest() {
        runExceptionTest(INPUT_EXP_ERR, CLASS_ITE);
    }

    @Test
    public void operatorModuloExceptionTest() {
        runExceptionTest(INPUT_MOD_ERR, CLASS_ITE);
    }

}
