package feature;

import uk.co.alexbroadbent.eval.ExpressionException;
import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.XList;
import framework.FunctionTest;
import uk.co.alexbroadbent.lexer.UnknownSequenceException;
import org.junit.Test;
import uk.co.alexbroadbent.parser.ParserException;

/**
 * x++.function
 *
 * @author Alexander Broadbent
 * @version 22/03/2016
 */
public class UserFunctionTest extends FunctionTest {

    private final static String  VAR_D       = "d";
    private final static String  VAR_T       = "t";
    private final static Integer VAR_D_VALUE = 5;
    private final static XList   VAR_T_VALUE = XList.parse(1, 2, 3);

    private final static String                     INPUT_SQUARE_FUNC       = "func square(x) = x * x";
    private final static String                     INPUT_COMBI_FUNC        = "func times(c, d) = c * d";
    private final static String                     INPUT_BIGGER_FUNC       = "func bigger(a, b) = a > b ? a : b";
    private final static String                     INPUT_NO_ARG_FUNC       = "func noargs() = x";
    private final static String                     INPUT_BAD_NAME_FUNC     = "func TRUE() = 2";
    private final static String                     INPUT_INVALID_FUNC      = "func circ(diameter) = diameter * pi /";
    private final static String                     INPUT_INVALID_VARS_FUNC = "func circ(diameter) = diameter * pi * ip";
    private final static String                     INPUT_MIXED_VAR_FUNC    = "func circ(diameter) = diameter * pi";
    private final static String                     INPUT_MORE_ARGS_FUNC    = "func test(a) = a";
    private final static String                     INPUT_DBL_LIST_FUNC     = "func double(l) = for x in l do x*2";
    private final static String                     INPUT_TRIPLE_FUNC       = "func triple(x) = x * 3";
    private final static String                     INPUT_SQUARE_RUN        = "square(4)";
    private final static String                     INPUT_COMBI_RUN         = "times(d, 7)";
    private final static String                     INPUT_BIGGER_RUN        = "bigger(4, 7)";
    private final static String                     INPUT_DBL_LIST_RUN      = "double(t)";
    private final static String                     INPUT_MIXED_VAR_RUN     = "circ(3)";
    private final static String                     INPUT_MORE_ARGS_RUN     = "test(3, 4)";
    private final static String                     INPUT_PREC_RUN          = "2 + square(d) * 2";
    private final static String                     INPUT_TRIPLE_RUN        = "for x in t do triple(x)";
    private final static String                     NAME_SQUARE_FUNC        = "square";
    private final static String                     NAME_BIGGER_FUNC        = "bigger";
    private final static String                     NAME_COMBI_FUNC         = "times";
    private final static String                     NAME_DBL_LIST_FUNC      = "double";
    private final static String                     NAME_MIXED_VAR_FUNC     = "circ";
    private final static String                     NAME_MORE_ARGS_FUNC     = "test";
    private final static String                     NAME_TRIPLE_FUNC        = "triple";
    private final static Double                     RESULT_SQUARE           = 16d;
    private final static Integer                    RESULT_BIGGER           = 7;
    private final static Double                     RESULT_COMBI            = 35d;
    private final static Double                     RESULT_PREC             = 52d;
    private final static Double                     RESULT_MIXED_VAR        = Math.PI * 3d;
    private final static XList                      RESULT_DBL_LIST         = XList.parse(2d, 4d, 6d);
    private final static XList                      RESULT_TRIPLE           = XList.parse(3d, 6d, 9d);
    private final static Class<? extends Exception> CLASS_EE                = ExpressionException.class;
    private final static Class<? extends Exception> CLASS_PE                = ParserException.class;


    @Override
    public void setUp() {
        super.setUp();

        setDomainVariable(VAR_D, VAR_D_VALUE);
        setDomainVariable(VAR_T, VAR_T_VALUE);
    }


    @Test
    public void singleVariableFunctionTest()
            throws UnknownSequenceException, ParserException, ExpressionException, IncomparableTypeException {
        runFunctionTest(INPUT_SQUARE_FUNC, NAME_SQUARE_FUNC, INPUT_SQUARE_RUN, RESULT_SQUARE);
    }

    @Test
    public void multiVariableFunctionTest()
            throws UnknownSequenceException, ParserException, ExpressionException, IncomparableTypeException {
        runFunctionTest(INPUT_BIGGER_FUNC, NAME_BIGGER_FUNC, INPUT_BIGGER_RUN, RESULT_BIGGER);
    }

    @Test
    public void combinationVariableFunctionTest()
            throws UnknownSequenceException, ParserException, ExpressionException, IncomparableTypeException {
        runFunctionTest(INPUT_COMBI_FUNC, NAME_COMBI_FUNC, INPUT_COMBI_RUN, RESULT_COMBI);
    }

    @Test
    public void noArgumentFunctionTest() {
        runExceptionTest(INPUT_NO_ARG_FUNC, CLASS_EE);
    }

    @Test
    public void nameClashFunctionTest() {
        runExceptionTest(INPUT_BAD_NAME_FUNC, CLASS_EE);
    }

    @Test
    public void invalidFunctionExceptionTest() {
        runExceptionTest(INPUT_INVALID_FUNC, CLASS_EE);
    }

    @Test
    public void invalidVariablesExceptionTest() {
        runExceptionTest(INPUT_INVALID_VARS_FUNC, CLASS_EE);
    }

    @Test
    public void variableFromDomainFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runFunctionTest(INPUT_MIXED_VAR_FUNC, NAME_MIXED_VAR_FUNC, INPUT_MIXED_VAR_RUN, RESULT_MIXED_VAR);
    }

    @Test
    public void tooManyArgumentsInCallTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runFunctionDeclaration(INPUT_MORE_ARGS_FUNC, NAME_MORE_ARGS_FUNC);
        runExceptionTest(INPUT_MORE_ARGS_RUN, CLASS_EE);
    }

    @Test
    public void redeclareExistingFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runFunctionDeclaration(INPUT_COMBI_FUNC, NAME_COMBI_FUNC);
        runExceptionTest(INPUT_COMBI_FUNC, CLASS_PE);
    }

    @Test
    public void loopInFunctionListTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runFunctionDeclaration(INPUT_DBL_LIST_FUNC, NAME_DBL_LIST_FUNC);
        runExpressionTest(INPUT_DBL_LIST_RUN, RESULT_DBL_LIST);
    }

    @Test
    public void precedenceUserFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runFunctionDeclaration(INPUT_SQUARE_FUNC, NAME_SQUARE_FUNC);
        runExpressionTest(INPUT_PREC_RUN, RESULT_PREC);
    }

    @Test
    public void multipleFunctionsTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runFunctionDeclaration(INPUT_TRIPLE_FUNC, NAME_TRIPLE_FUNC);
        runExpressionTest(INPUT_TRIPLE_RUN, RESULT_TRIPLE);
    }

}
