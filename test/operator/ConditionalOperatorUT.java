package operator;

import eval.ExpressionException;
import eval.IncomparableTypeException;
import eval.XList;
import framework.ExpressionTest;
import lexer.UnknownSequenceException;
import org.junit.Test;
import parser.ParserException;

/**
 * x++.operator
 *
 * @author Alexander Broadbent
 * @version 08/01/2016
 */
public class ConditionalOperatorUT extends ExpressionTest {

    private static final String  VAR_V       = "v";
    private static final String  VAR_W       = "w";
    private static final String  VAR_X       = "x";
    private static final String  VAR_Y       = "y";
    private static final String  VAR_Z       = "z";
    private static final Integer VAR_W_VALUE = 3;
    private static final Integer VAR_X_VALUE = 12;
    private static final Integer VAR_Y_VALUE = 6;
    private static final Boolean VAR_Z_VALUE = Boolean.TRUE;

    private static final String                     INPUT_SIM        = "(x > y) ? x : y";
    private static final String                     INPUT_SIM_2      = "z ? y : x";
    private static final String                     INPUT_COM        = "(x < y + 3) ? x + 1 / 2 : ( 2 / 3 ) * y";
    private static final String                     INPUT_COM_2      = "(x > y) ? (x/3)^(y/3) : y^w";
    private static final String                     INPUT_ASSIGN     = "v = (x > y) ? x : y";
    private static final String                     INPUT_FUNC       = "(x < y) ? list(x, 1) : list(y, 2)";
    private static final String                     INPUT_MISS_ELSE  = "(x < y+3) ? x y";
    private static final String                     INPUT_INCOMPAT   = "(x) ? x : y";
    private static final String                     INPUT_INCOMPAT_2 = "(3) ? x : y";
    private static final Integer                    RESULT_SIM       = 12;
    private static final Integer                    RESULT_SIM_2     = 6;
    private static final Double                     RESULT_COM       = 4d;
    private static final Double                     RESULT_COM_2     = 16d;
    private static final Integer                    RESULT_ASSIGN    = 12;
    private static final XList                      RESULT_FUNC      = XList.parse(6, 2);
    private static final Class<? extends Exception> CLASS_EE         = ExpressionException.class;
    private static final Class<? extends Exception> CLASS_ITE        = IncomparableTypeException.class;

    @Override
    public void setUp() {
        super.setUp();

        setDomainVariable(VAR_W, VAR_W_VALUE);
        setDomainVariable(VAR_X, VAR_X_VALUE);
        setDomainVariable(VAR_Y, VAR_Y_VALUE);
        setDomainVariable(VAR_Z, VAR_Z_VALUE);
    }

    @Test
    public void simpleTest()
            throws UnknownSequenceException, ExpressionException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_SIM, RESULT_SIM);
    }

    @Test
    public void simple2Test()
            throws UnknownSequenceException, ExpressionException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_SIM_2, RESULT_SIM_2);
    }

    @Test
    public void complexTest()
            throws UnknownSequenceException, ExpressionException, IncomparableTypeException, ParserException {
        runExpressionTest("Complex Conditional[" + INPUT_COM + "]", INPUT_COM, RESULT_COM);
    }

    @Test
    public void complex2Test()
            throws UnknownSequenceException, ExpressionException, IncomparableTypeException, ParserException {
        runExpressionTest("Complex Conditional[" + INPUT_COM_2 + "]", INPUT_COM_2, RESULT_COM_2);
    }

    @Test
    public void assignmentTest()
            throws UnknownSequenceException, ExpressionException, IncomparableTypeException, ParserException {
        runVariableTest(INPUT_ASSIGN, VAR_V, RESULT_ASSIGN);
    }

    @Test
    public void functionTest()
            throws UnknownSequenceException, ExpressionException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_FUNC, RESULT_FUNC);
    }

    @Test
    public void errorMissingFalseConditionTest() {
        runExceptionTest(INPUT_MISS_ELSE, CLASS_EE);
    }

    @Test
    public void nonBooleanConditionTest() {
        runExceptionTest(INPUT_INCOMPAT, CLASS_ITE);
    }

    @Test
    public void nonBooleanCondition2Test() {
        runExceptionTest(INPUT_INCOMPAT_2, CLASS_ITE);
    }


}
