package operator;

import framework.ExpressionTest;
import lexer.UnknownSequenceException;
import org.junit.Test;
import parser.ExpressionException;
import parser.IncomparableTypeException;
import parser.ParserException;

/**
 * x++.operator
 * <p>
 * <p>
 * Test the mathematical functions:
 * <ul>
 * <li>Sine</li>
 * <li>Cosine</li>
 * <li>Tangent</li>
 * <li>ASine</li>
 * <li>ACosine</li>
 * <li>ATangent</li>
 * <li>Power</li>
 * <li>Square root</li>
 * <li>Natural log (ln)</li>
 * <li>Log Base 10 (log10)</li>
 * <li>Exponential</li>
 * <li>Modulo</li>
 * </ul>
 * </p>
 *
 * @author Alexander Broadbent
 * @version 04/01/2016
 */
public class MathOperatorUT extends ExpressionTest {

    private static final String INPUT_SIN   = "sin(1)";
    private static final String INPUT_COS   = "cos(0.5)";
    private static final String INPUT_TAN   = "tan(1.5)";
    private static final String INPUT_ASIN  = "asin(1)";
    private static final String INPUT_ACOS  = "acos(.5)";
    private static final String INPUT_ATAN  = "atan(1.5)";
    private static final String INPUT_POW   = "2^4";
    private static final String INPUT_SQR   = "sqrt(9)";
    private static final String INPUT_NLN   = "ln(4)";
    private static final String INPUT_L10   = "log10(2)";
    private static final String INPUT_EXP   = "exp(4)";
    private static final String INPUT_MOD   = "4%2";
    private static final Double RESULT_SIN  = Math.sin(1);
    private static final Double RESULT_COS  = Math.cos(0.5);
    private static final Double RESULT_TAN  = Math.tan(1.5);
    private static final Double RESULT_ASIN = Math.asin(1);
    private static final Double RESULT_ACOS = Math.acos(0.5);
    private static final Double RESULT_ATAN = Math.atan(1.5);
    private static final Double RESULT_POW  = Math.pow(2, 4);
    private static final Double RESULT_SQR  = Math.sqrt(9);
    private static final Double RESULT_NLN  = Math.log(4);
    private static final Double RESULT_L10  = Math.log10(2);
    private static final Double RESULT_EXP  = Math.exp(4);
    private static final Double RESULT_MOD  = (double) (4 % 2);


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

}
