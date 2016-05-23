package function;

import eval.ExpressionException;
import eval.IncomparableTypeException;
import eval.NoValueException;
import eval.XList;
import framework.FunctionTest;
import lexer.UnknownSequenceException;
import org.junit.Test;
import parser.ParserException;

/**
 * x++.function
 *
 * @author Alexander Broadbent
 * @version 24/03/2016
 */
public class PredefinedFunctionUT extends FunctionTest {

    private static final String  VAR_W       = "w";
    private static final String  VAR_X       = "x";
    private static final String  VAR_Y       = "y";
    private static final String  VAR_Z       = "z";
    private static final Integer VAR_W_VALUE = 15;
    private static final Integer VAR_X_VALUE = 10;
    private static final Integer VAR_Y_VALUE = 4;
    private static final XList   VAR_Z_VALUE = XList.parse(1, 2, 3);

    private static final String                     INPUT_CONS       = "cons(y, z)";
    private static final String                     INPUT_CONS_ARG   = "cons(y, z, 3)";
    private static final String                     INPUT_CONS_TYPE  = "cons(3, false)";
    private static final String                     INPUT_HEAD       = "head(z)";
    private static final String                     INPUT_HEAD_ARG   = "head(z, 2)";
    private static final String                     INPUT_HEAD_TYPE  = "head(2)";
    private static final String                     INPUT_LIST       = "list(1, 2, 3)";
    private static final String                     INPUT_MAX        = "max(x, 2, 40, 24, 30)";
    private static final String                     INPUT_MAX_TYPE   = "max(true, x)";
    private static final String                     INPUT_SUM        = "sum(y, 16, w, 15, 5, 5)";
    private static final String                     INPUT_SUM_TYPE   = "sum(x, true, z)";
    private static final String                     INPUT_TAIL       = "tail(z)";
    private static final String                     INPUT_TAIL_ARG   = "tail(z, true)";
    private static final String                     INPUT_TAIL_TYPE  = "tail(true)";
    private static final String                     INPUT_EMPTY      = "empty(z)";
    private static final String                     INPUT_EMPTY_ARG  = "empty(z, 2)";
    private static final String                     INPUT_EMPTY_TYPE = "empty(x)";
    private static final String                     INPUT_SIZE       = "size(z)";
    private static final String                     INPUT_SIZE_ARG   = "size(z, 2)";
    private static final String                     INPUT_SIZE_TYPE  = "size(2)";
    private static final String                     INPUT_RANDOM     = "random()";
    private static final XList                      RESULT_CONS      = XList.parse(4, 1, 2, 3);
    private static final Integer                    RESULT_HEAD      = 1;
    private static final XList                      RESULT_LIST      = XList.parse(1, 2, 3);
    private static final Double                     RESULT_MAX       = 40d;
    private static final Double                     RESULT_SUM       = 60d;
    private static final XList                      RESULT_TAIL      = XList.parse(2, 3);
    private static final Boolean                    RESULT_EMPTY     = Boolean.FALSE;
    private static final Integer                    RESULT_SIZE      = 3;
    private static final Class<? extends Exception> CLASS_ITE        = IncomparableTypeException.class;
    private static final Class<? extends Exception> CLASS_EE         = ExpressionException.class;


    @Override
    public void setUp() {
        super.setUp();

        setDomainVariable(VAR_W, VAR_W_VALUE);
        setDomainVariable(VAR_X, VAR_X_VALUE);
        setDomainVariable(VAR_Y, VAR_Y_VALUE);
        setDomainVariable(VAR_Z, VAR_Z_VALUE);
    }

    @Test
    public void consFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException, NoValueException {
        runExpressionTest(INPUT_CONS, RESULT_CONS);
    }

    @Test
    public void consTooManyArgsTest() {
        runExceptionTest(INPUT_CONS_ARG, CLASS_EE);
    }

    @Test
    public void consIncomparableTypeTest() {
        runExceptionTest(INPUT_CONS_TYPE, CLASS_ITE);
    }

    @Test
    public void headFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException, NoValueException {
        runExpressionTest(INPUT_HEAD, RESULT_HEAD);
    }

    @Test
    public void headTooManyArgsTest() {
        runExceptionTest(INPUT_HEAD_ARG, CLASS_EE);
    }

    @Test
    public void headIncomparableTypeTest() {
        runExceptionTest(INPUT_HEAD_TYPE, CLASS_ITE);
    }

    @Test
    public void listFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException, NoValueException {
        runExpressionTest(INPUT_LIST, RESULT_LIST);
    }

    @Test
    public void maxFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException, NoValueException {
        runExpressionTest(INPUT_MAX, RESULT_MAX);
    }

    @Test
    public void maxIncomparableTypeTest() {
        runExceptionTest(INPUT_MAX_TYPE, CLASS_ITE);
    }

    @Test
    public void sumFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException, NoValueException {
        runExpressionTest(INPUT_SUM, RESULT_SUM);
    }

    @Test
    public void sumIncomparableTypeTest() {
        runExceptionTest(INPUT_SUM_TYPE, CLASS_ITE);
    }

    @Test
    public void tailFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException, NoValueException {
        runExpressionTest(INPUT_TAIL, RESULT_TAIL);
    }

    @Test
    public void tailTooManyArgsTest() {
        runExceptionTest(INPUT_TAIL_ARG, CLASS_EE);
    }

    @Test
    public void tailIncomparableTypeTest() {
        runExceptionTest(INPUT_TAIL_TYPE, CLASS_ITE);
    }

    @Test
    public void emptyFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException, NoValueException {
        runExpressionTest(INPUT_EMPTY, RESULT_EMPTY);
    }

    @Test
    public void emptyTooManyArgsTest() {
        runExceptionTest(INPUT_EMPTY_ARG, CLASS_EE);
    }

    @Test
    public void emptyIncomparableTypeTest() {
        runExceptionTest(INPUT_EMPTY_TYPE, CLASS_ITE);
    }

    @Test
    public void sizeFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException, NoValueException {
        runExpressionTest(INPUT_SIZE, RESULT_SIZE);
    }

    @Test
    public void sizeTooManyArgsExceptionTest() {
        runExceptionTest(INPUT_SIZE_ARG, CLASS_EE);
    }

    @Test
    public void sizeIncomparableTypeTest() {
        runExceptionTest(INPUT_SIZE_TYPE, CLASS_ITE);
    }

    @Test
    public void randomFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException, NoValueException {
        Object result = getResultFromInput(INPUT_RANDOM);
        assertTypeOfResult(result, Double.class);
        assertResultRange(((Double) result), 0d, 1d);
    }

}
