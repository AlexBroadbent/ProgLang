package operator;

import com.google.common.collect.Lists;
import eval.Literal;
import framework.ExpressionTest;
import lexer.UnknownSequenceException;
import org.junit.Test;
import parser.ExpressionException;
import parser.IncomparableTypeException;
import parser.ParserException;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * ProgLang.operator
 *
 * @author Alexander Broadbent
 * @version 26/03/2016
 */
public class ListOperatorUT extends ExpressionTest {

    private final static String                     VAR_T              = "t";
    private final static LinkedList<Literal>        VAR_T_VALUE        = Lists.newLinkedList(Arrays.asList(wrap(1), wrap(2), wrap(3)));

    private final static String                     INPUT_ACCESS       = "t[2]";
    private final static String                     INPUT_ACCESS_ERR_2 = "2[2]";
    private final static String                     INPUT_ACCESS_ERR_3 = "t[t]";
    private static final String                     INPUT_CREATE       = "{1, 3, 5}";
    private static final String                     INPUT_CREATE_ERR   = "1, 3, 5}";
    private final static String                     INPUT_ACCESS_ERR   = "t[4]";
    private final static Integer                    RESULT_ACCESS      = 3;
    private static final LinkedList<Literal>        RESULT_CREATE      = Lists.newLinkedList(Arrays.asList(wrap(1), wrap(3), wrap(5)));
    private final static Class<? extends Exception> CLASS_CREATE_ERR   = ExpressionException.class;
    private final static Class<? extends Exception> CLASS_ACCESS_ERR   = ExpressionException.class;
    private final static Class<? extends Exception> CLASS_INCOMPARABLE = IncomparableTypeException.class;


    @Override
    public void setUp() {
        super.setUp();

        setDomainVariable(VAR_T, VAR_T_VALUE);
    }


    @Test
    public void arrayOperatorTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runListTest(INPUT_CREATE, RESULT_CREATE);
    }

    @Test
    public void arrayOperatorExceptionTest() {
        runExceptionTest(INPUT_CREATE_ERR, CLASS_CREATE_ERR);
    }

    @Test
    public void arrayAccessTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_ACCESS, RESULT_ACCESS);
    }

    @Test
    public void arrayAccessExceptionTest() {
        runExceptionTest(INPUT_ACCESS_ERR, CLASS_ACCESS_ERR);
    }

    @Test
    public void arrayAccessListTypeExceptionTest() {
        runExceptionTest(INPUT_ACCESS_ERR_2, CLASS_INCOMPARABLE);
    }

    @Test
    public void arrayAccessPositionTypeExceptionTest() {
        runExceptionTest(INPUT_ACCESS_ERR_3, CLASS_ACCESS_ERR);
    }

}
