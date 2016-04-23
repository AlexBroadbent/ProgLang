package feature;

import eval.ExpressionException;
import eval.IncomparableTypeException;
import eval.XList;
import framework.FunctionTest;
import lexer.UnknownSequenceException;
import org.junit.Test;
import parser.ParserException;

/**
 * x++.operator.loop
 *
 * @author Alexander Broadbent
 * @version 28/03/2016
 */
public class ForLoopTest extends FunctionTest {

    private final static String VAR_T       = "t";
    private final static String VAR_S       = "s";
    private final static XList  VAR_T_VALUE = XList.parse(1, 2, 3);
    private final static XList  VAR_S_VALUE = XList.parse(0, 1, 2);

    private final static String                     INPUT_VAR      = "for x in t do x";
    private final static String                     INPUT_LIST     = "for x in list(4, 5, 6) do x";
    private final static String                     INPUT_CALC     = "for x in list(2, 5, 70) do x*2";
    private final static String                     INPUT_OPER     = "for x in s do s[x]";
    private final static String                     INPUT_SHORT    = "for x t x";
    private final static String                     INPUT_NO_DO    = "for x in x";
    private final static String                     INPUT_NO_VAR   = "for in s do x";
    private final static String                     INPUT_NO_LIST  = "for x in x";
    private final static String                     INPUT_DBL_FUNC = "func double(l) = for x in l do x*2";
    private final static String                     NAME_DBL       = "double";
    private final static String                     INPUT_DBL_RUN  = "double(t)";
    private final static String                     ERROR_VAR      = "for true in t do x";
    private final static String                     ERROR_LIST     = "for x in true do x";
    private final static String                     ERROR_LIST_2   = "for x in head do x";
    private final static XList                      RESULT_VAR     = XList.parse(1, 2, 3);
    private final static XList                      RESULT_LIST    = XList.parse(4, 5, 6);
    private final static XList                      RESULT_CALC    = XList.parse(4d, 10d, 140d);
    private final static XList                      RESULT_OPER    = XList.parse(0, 1, 2);
    private final static XList                      RESULT_SHORT   = XList.parse(1, 2, 3);
    private final static XList                      RESULT_DBL_RUN = XList.parse(2d, 4d, 6d);
    private final static Class<? extends Exception> CLASS_EE       = ExpressionException.class;


    @Override
    public void setUp() {
        super.setUp();

        setDomainVariable(VAR_T, VAR_T_VALUE);
        setDomainVariable(VAR_S, VAR_S_VALUE);
    }


    @Test
    public void loopWithVariableTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_VAR, RESULT_VAR);
    }

    @Test
    public void loopWithInlineListTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_LIST, RESULT_LIST);
    }

    @Test
    public void loopCalculationTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_CALC, RESULT_CALC);
    }

    @Test
    public void loopAccessTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_OPER, RESULT_OPER);
    }

    @Test
    public void loopShortInputTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_SHORT, RESULT_SHORT);
    }

    @Test
    public void funcWithLoopTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runFunctionTest(INPUT_DBL_FUNC, NAME_DBL, INPUT_DBL_RUN, RESULT_DBL_RUN);
    }

    @Test
    public void inputNoDoExceptionTest() {
        runExceptionTest(INPUT_NO_DO, CLASS_EE);
    }

    @Test
    public void inputNoVarExceptionTest() {
        runExceptionTest(INPUT_NO_VAR, CLASS_EE);
    }

    @Test
    public void inputNoListExceptionTest() {
        runExceptionTest(INPUT_NO_LIST, CLASS_EE);
    }

    @Test
    public void incomparableVariableTypeExceptionTest() {
        runExceptionTest(ERROR_VAR, CLASS_EE);
    }

    @Test
    public void incomparableListTypeExceptionTest() {
        runExceptionTest(ERROR_LIST_2, CLASS_EE);
    }

    @Test
    public void wrongListTypeExceptionTest() {
        runExceptionTest(ERROR_LIST, CLASS_EE);
    }

}