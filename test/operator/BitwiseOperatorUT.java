package operator;

import eval.ExpressionException;
import eval.IncomparableTypeException;
import framework.ExpressionTest;
import lexer.UnknownSequenceException;
import org.junit.Test;
import parser.ParserException;

/**
 * x++.operator
 * <p>
 * Verified results using:
 * http://www.miniwebtool.com/bitwise-calculator/
 * http://www.miniwebtool.com/bitwise-calculator/bit-shift/
 *
 * @author Alexander Broadbent
 * @version 30/12/2015
 */
public class BitwiseOperatorUT extends ExpressionTest {

    private static final String  VAR_X            = "x";
    private static final String  VAR_Y            = "y";
    private static final String  VAR_X_BINARY     = "binaryX";
    private static final String  VAR_Y_BINARY     = "binaryY";
    private static final Integer VAR_X_VAL        = 5;
    private static final Integer VAR_Y_VAL        = 3;
    private static final Integer VAR_X_VAL_BINARY = Integer.parseUnsignedInt("00011010", 2); // Same method as Literal parser
    private static final Integer VAR_Y_VAL_BINARY = 2;

    private static final String                     INPUT_NOT          = "~x";
    private static final String                     INPUT_NOT_ERR      = "~'hello'";
    private static final String                     INPUT_AND          = "x & y";
    private static final String                     INPUT_AND_ERR      = "x & true";
    private static final String                     INPUT_OR           = "x | y";
    private static final String                     INPUT_OR_ERR       = "x | false";
    private static final String                     INPUT_XOR          = "x $ y";
    private static final String                     INPUT_XOR_ERR      = "x $ 'hello'";
    private static final String                     INPUT_LBS          = "binaryX << binaryY";
    private static final String                     INPUT_LBS_ERR      = "binaryX << 'hello'";
    private static final String                     INPUT_RBS          = "binaryX >> binaryY";
    private static final String                     INPUT_RBS_ERR      = "binaryX >> false";
    private static final Integer                    RESULT_NOT         = -6;
    private static final Integer                    RESULT_AND         = 1;
    private static final Integer                    RESULT_OR          = 7;
    private static final Integer                    RESULT_XOR         = 6;
    private static final Integer                    RESULT_LBS         = 104;
    private static final Integer                    RESULT_RBS         = 6;
    private static final Class<? extends Exception> CLASS_INCOMPARABLE = IncomparableTypeException.class;


    @Override
    public void setUp() {
        super.setUp();

        setDomainVariable(VAR_X, VAR_X_VAL);
        setDomainVariable(VAR_Y, VAR_Y_VAL);
        setDomainVariable(VAR_X_BINARY, VAR_X_VAL_BINARY);
        setDomainVariable(VAR_Y_BINARY, VAR_Y_VAL_BINARY);
    }

    @Test
    public void bitwiseNotTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_NOT, RESULT_NOT);
    }

    @Test
    public void bitwiseAndTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_AND, RESULT_AND);
    }

    @Test
    public void bitwiseOrTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_OR, RESULT_OR);
    }

    @Test
    public void bitwiseXorTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_XOR, RESULT_XOR);
    }

    @Test
    public void bitwiseLeftShiftTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_LBS, RESULT_LBS);
    }

    @Test
    public void bitwiseRightShiftTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_RBS, RESULT_RBS);
    }

    @Test
    public void bitwiseNotExceptionTest() {
        runExceptionTest(INPUT_NOT_ERR, CLASS_INCOMPARABLE);
    }

    @Test
    public void bitwiseAndExceptionTest() {
        runExceptionTest(INPUT_AND_ERR, CLASS_INCOMPARABLE);
    }

    @Test
    public void bitwiseOrExceptionTest() {
        runExceptionTest(INPUT_OR_ERR, CLASS_INCOMPARABLE);
    }

    @Test
    public void bitwiseXorExceptionTest() {
        runExceptionTest(INPUT_XOR_ERR, CLASS_INCOMPARABLE);
    }

    @Test
    public void bitwiseLeftShiftExceptionTest() {
        runExceptionTest(INPUT_LBS_ERR, CLASS_INCOMPARABLE);
    }

    @Test
    public void bitwiseRightShiftExceptionTest() {
        runExceptionTest(INPUT_RBS_ERR, CLASS_INCOMPARABLE);
    }

}
