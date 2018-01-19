package type;

import eval.ExpressionException;
import framework.ExpressionTest;
import org.junit.Test;

/**
 * x++.type
 *
 * @author Alexander Broadbent
 * @version 20/03/2016
 */
public class BitwiseByteParseTest extends ExpressionTest {

    private static final String VAR_X       = "x";
    private static final Double VAR_X_VALUE = 200d;
    private static final String VAR_Y       = "y";
    private static final Double VAR_Y_VALUE = -200d;

    private static final String                     INPUT_AND_ARG1_MIN = "y & 1";
    private static final String                     INPUT_AND_ARG2_MIN = "1 & y";
    private static final String                     INPUT_AND_ARG1_MAX = "x & 1";
    private static final String                     INPUT_AND_ARG2_MAX = "1 & x";
    private static final String                     INPUT_LS_ARG1_MIN  = "y << 1";
    private static final String                     INPUT_LS_ARG2_MIN  = "1 << y";
    private static final String                     INPUT_LS_ARG1_MAX  = "x << 1";
    private static final String                     INPUT_LS_ARG2_MAX  = "1 << x";
    private static final String                     INPUT_NOT_MIN      = "~y";
    private static final String                     INPUT_NOT_MAX      = "~x";
    private static final String                     INPUT_OR_ARG1_MIN  = "y | 1";
    private static final String                     INPUT_OR_ARG2_MIN  = "1 | y";
    private static final String                     INPUT_OR_ARG1_MAX  = "x | 1";
    private static final String                     INPUT_OR_ARG2_MAX  = "1 | x";
    private static final String                     INPUT_RS_ARG1_MIN  = "y >> 1";
    private static final String                     INPUT_RS_ARG2_MIN  = "1 >> y";
    private static final String                     INPUT_RS_ARG1_MAX  = "x >> 1";
    private static final String                     INPUT_RS_ARG2_MAX  = "1 >> x";
    private static final String                     INPUT_XOR_ARG1_MIN = "y $ 1";
    private static final String                     INPUT_XOR_ARG2_MIN = "1 $ y";
    private static final String                     INPUT_XOR_ARG1_MAX = "x $ 1";
    private static final String                     INPUT_XOR_ARG2_MAX = "1 $ x";
    private static final Class<? extends Exception> CLASS_EE           = ExpressionException.class;


    @Override
    public void setUp() {
        super.setUp();

        setDomainVariable(VAR_X, VAR_X_VALUE);
        setDomainVariable(VAR_Y, VAR_Y_VALUE);
    }


    @Test
    public void andArg1MinExceptionTest() {
        runExceptionTest(INPUT_AND_ARG1_MIN, CLASS_EE);
    }

    @Test
    public void andArg2MinExceptionTest() {
        runExceptionTest(INPUT_AND_ARG2_MIN, CLASS_EE);
    }

    @Test
    public void andArg1MaxExceptionTest() {
        runExceptionTest(INPUT_AND_ARG1_MAX, CLASS_EE);
    }

    @Test
    public void andArg2MaxExceptionTest() {
        runExceptionTest(INPUT_AND_ARG2_MAX, CLASS_EE);
    }


    @Test
    public void lsArg1MinExceptionTest() {
        runExceptionTest(INPUT_LS_ARG1_MIN, CLASS_EE);
    }

    @Test
    public void lsArg2MinExceptionTest() {
        runExceptionTest(INPUT_LS_ARG2_MIN, CLASS_EE);
    }

    @Test
    public void lsArg1MaxExceptionTest() {
        runExceptionTest(INPUT_LS_ARG1_MAX, CLASS_EE);
    }

    @Test
    public void lsArg2MaxExceptionTest() {
        runExceptionTest(INPUT_LS_ARG2_MAX, CLASS_EE);
    }


    @Test
    public void notMinExceptionTest() {
        runExceptionTest(INPUT_NOT_MIN, CLASS_EE);
    }

    @Test
    public void notMaxExceptionTest() {
        runExceptionTest(INPUT_NOT_MAX, CLASS_EE);
    }


    @Test
    public void orArg1MinExceptionTest() {
        runExceptionTest(INPUT_OR_ARG1_MIN, CLASS_EE);
    }

    @Test
    public void orArg2MinExceptionTest() {
        runExceptionTest(INPUT_OR_ARG2_MIN, CLASS_EE);
    }

    @Test
    public void orArg1MaxExceptionTest() {
        runExceptionTest(INPUT_OR_ARG1_MAX, CLASS_EE);
    }

    @Test
    public void orArg2MaxExceptionTest() {
        runExceptionTest(INPUT_OR_ARG2_MAX, CLASS_EE);
    }


    @Test
    public void rsArg1MinExceptionTest() {
        runExceptionTest(INPUT_RS_ARG1_MIN, CLASS_EE);
    }

    @Test
    public void rsArg2MinExceptionTest() {
        runExceptionTest(INPUT_RS_ARG2_MIN, CLASS_EE);
    }

    @Test
    public void rsArg1MaxExceptionTest() {
        runExceptionTest(INPUT_RS_ARG1_MAX, CLASS_EE);
    }

    @Test
    public void rsArg2MaxExceptionTest() {
        runExceptionTest(INPUT_RS_ARG2_MAX, CLASS_EE);
    }


    @Test
    public void xorArg1MinExceptionTest() {
        runExceptionTest(INPUT_XOR_ARG1_MIN, CLASS_EE);
    }

    @Test
    public void xorArg2MinExceptionTest() {
        runExceptionTest(INPUT_XOR_ARG2_MIN, CLASS_EE);
    }

    @Test
    public void xorArg1MaxExceptionTest() {
        runExceptionTest(INPUT_XOR_ARG1_MAX, CLASS_EE);
    }

    @Test
    public void xorArg2MaxExceptionTest() {
        runExceptionTest(INPUT_XOR_ARG2_MAX, CLASS_EE);
    }

}
