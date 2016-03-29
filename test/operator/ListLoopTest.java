package operator;

import eval.Literal;
import framework.ExpressionTest;
import lexer.UnknownSequenceException;
import org.junit.Test;
import parser.ExpressionException;
import parser.IncomparableTypeException;
import parser.ParserException;

import java.util.List;

/**
 * ProgLang.operator.loop
 *
 * @author Alexander Broadbent
 * @version 28/03/2016
 */
public class ListLoopTest extends ExpressionTest {

    private final static String                     VAR_T         = "t";
    private final static String                     VAR_S         = "s";
    private final static List<Literal>              VAR_T_VALUE   = createLiteralLinkedList(1, 2, 3);
    private final static List<Literal>              VAR_S_VALUE   = createLiteralLinkedList(0, 1, 2);

    private final static String                     INPUT_VAR     = "for x in t do x";
    private final static String                     INPUT_LIST    = "for x in list(4, 5, 6) do x";
    private final static String                     INPUT_CALC    = "for x in list(2, 5, 70) do x*2";
    private final static String                     INPUT_OPER    = "for x in s do s[x]";
    private final static String                     INPUT_SHORT   = "for x t x";
    private final static String                     INPUT_NO_DO   = "for x in x";
    private final static String                     INPUT_NO_VAR  = "for in s x";
    private final static String                     INPUT_NO_LIST = "for x in x";
    private final static List<Literal>              RESULT_VAR    = createLiteralList(1, 2, 3);
    private final static List<Literal>              RESULT_LIST   = createLiteralList(4, 5, 6);
    private final static List<Literal>              RESULT_CALC   = createLiteralList(4d, 10d, 140d);
    private final static List<Literal>              RESULT_OPER   = createLiteralList(0, 1, 2);
    private final static List<Literal>              RESULT_SHORT  = createLiteralList(1, 2, 3);
    private final static Class<? extends Exception> CLASS_EE      = ExpressionException.class;


    @Override
    public void setUp() {
        super.setUp();

        setDomainVariable(VAR_T, VAR_T_VALUE);
        setDomainVariable(VAR_S, VAR_S_VALUE);
    }


    @Test
    public void loopWithVariableTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runListTest(INPUT_VAR, RESULT_VAR);
    }

    @Test
    public void loopWithInlineListTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runListTest(INPUT_LIST, RESULT_LIST);
    }

    @Test
    public void loopCalculationTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runListTest(INPUT_CALC, RESULT_CALC);
    }

    @Test
    public void loopAccessTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runListTest(INPUT_OPER, RESULT_OPER);
    }


    @Test
    public void loopShortInputTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runListTest(INPUT_SHORT, RESULT_SHORT);
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

}