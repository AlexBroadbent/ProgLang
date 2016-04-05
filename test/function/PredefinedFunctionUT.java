package function;

import eval.Literal;
import framework.FunctionTest;
import lexer.UnknownSequenceException;
import org.junit.Test;
import parser.ExpressionException;
import parser.IncomparableTypeException;
import parser.ParserException;

import java.util.List;

/**
 * x++.function
 *
 * @author Alexander Broadbent
 * @version 24/03/2016
 */
public class PredefinedFunctionUT extends FunctionTest {

    private static final String                     VAR_W               = "w";
    private static final String                     VAR_X               = "x";
    private static final String                     VAR_Y               = "y";
    private static final String                     VAR_Z               = "z";
    private static final Integer                    VAR_W_VALUE         = 15;
    private static final Integer                    VAR_X_VALUE         = 10;
    private static final Integer                    VAR_Y_VALUE         = 4;
    private static final List<Literal>              VAR_Z_VALUE         = createLiteralLinkedList(1, 2, 3);

    private static final String                     INPUT_CONS          = "cons(y, z)";
    private static final String                     INPUT_HEAD          = "head(z)";
    private static final String                     INPUT_LIST          = "list(1, 2, 3)";
    private static final String                     INPUT_MAX           = "max(x, 2, 40, 24, 30)";
    private static final String                     INPUT_SUM           = "sum(y, 16, w, 15, 5, 5)";
    private static final String                     INPUT_TAIL          = "tail(z)";
    private static final String                     INPUT_EMPTY         = "empty(z)";
    private static final String                     INPUT_EMPTY_ERR     = "empty(2)";
    private static final String                     INPUT_EMPTY_ERR_ARG = "empty(z, x)";
    private static final List<Literal>              RESULT_CONS         = createLiteralLinkedList(4, 1, 2, 3);
    private static final Integer                    RESULT_HEAD         = 1;
    private static final List<Literal>              RESULT_LIST         = createLiteralLinkedList(1, 2, 3);
    private static final Double                     RESULT_MAX          = 40d;
    private static final Double                     RESULT_SUM          = 60d;
    private static final List<Literal>              RESULT_TAIL         = createLiteralLinkedList(2, 3);
    private static final Boolean                    RESULT_EMPTY        = Boolean.FALSE;
    private static final Class<? extends Exception> CLASS_ITE           = IncomparableTypeException.class;
    private static final Class<? extends Exception> CLASS_EE            = ExpressionException.class;


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
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runListTest(INPUT_CONS, RESULT_CONS);
    }

    @Test
    public void headFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_HEAD, RESULT_HEAD);
    }

    @Test
    public void listFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runListTest(INPUT_LIST, RESULT_LIST);
    }

    @Test
    public void maxFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_MAX, RESULT_MAX);
    }

    @Test
    public void sumFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_SUM, RESULT_SUM);
    }

    @Test
    public void tailFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runListTest(INPUT_TAIL, RESULT_TAIL);
    }

    @Test
    public void emptyFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_EMPTY, RESULT_EMPTY);
    }

    @Test
    public void emptyFunctionIncomparableTest() {
        runExceptionTest(INPUT_EMPTY_ERR, CLASS_ITE);
    }

    @Test
    public void emptyFunctionMultipleArgsTest() {
        runExceptionTest(INPUT_EMPTY_ERR_ARG, CLASS_EE);
    }

}
