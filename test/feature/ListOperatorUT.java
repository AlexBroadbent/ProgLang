package feature;

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
 * @version 26/03/2016
 */
public class ListOperatorUT extends ExpressionTest {

    private final static String VAR_T       = "t";
    private final static XList  VAR_T_VALUE = XList.parse(1, 2, 3);

    private final static String                     INPUT_ACCESS       = "t[2]";
    private final static String                     INPUT_ACCESS_ERR_2 = "2[2]";
    private final static String                     INPUT_ACCESS_ERR_3 = "t[t]";
    private static final String                     INPUT_CREATE       = "{1, 3, 5}";
    private static final String                     INPUT_CREATE_ERR   = "1, 3, 5}";
    private final static String                     INPUT_ACCESS_ERR   = "t[4]";
    private final static String                     INPUT_MAX_FUNC     = "max(1, 2, t)";
    private final static String                     INPUT_SUM_FUNC     = "sum(1, 2, t)";
    private static final XList                      RESULT_CREATE      = XList.parse(1, 3, 5);
    private final static Integer                    RESULT_ACCESS      = 3;
    private final static Double                     RESULT_MAX_FUNC    = 3d;
    private final static Double                     RESULT_SUM_FUNC    = 9d;
    private final static Class<? extends Exception> CLASS_CREATE_ERR   = ExpressionException.class;
    private final static Class<? extends Exception> CLASS_ACCESS_ERR   = ExpressionException.class;
    private final static Class<? extends Exception> CLASS_INCOMPARABLE = IncomparableTypeException.class;


    @Override
    public void setUp() {
        super.setUp();

        setDomainVariable(VAR_T, VAR_T_VALUE);
    }


    @Test
    public void listOperatorTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runListTest(INPUT_CREATE, RESULT_CREATE);
    }

    @Test
    public void listOperatorExceptionTest() {
        runExceptionTest(INPUT_CREATE_ERR, CLASS_CREATE_ERR);
    }

    @Test
    public void listAccessTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_ACCESS, RESULT_ACCESS);
    }

    @Test
    public void listAccessExceptionTest() {
        runExceptionTest(INPUT_ACCESS_ERR, CLASS_ACCESS_ERR);
    }

    @Test
    public void listAccessListTypeExceptionTest() {
        runExceptionTest(INPUT_ACCESS_ERR_2, CLASS_INCOMPARABLE);
    }

    @Test
    public void listAccessPositionTypeExceptionTest() {
        runExceptionTest(INPUT_ACCESS_ERR_3, CLASS_ACCESS_ERR);
    }

    @Test
    public void listMaxFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_MAX_FUNC, RESULT_MAX_FUNC);
    }

    @Test
    public void listSumFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_SUM_FUNC, RESULT_SUM_FUNC);
    }

}
