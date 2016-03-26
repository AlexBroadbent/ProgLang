package function;

import framework.FunctionTest;
import lexer.UnknownSequenceException;
import org.junit.Test;
import parser.ExpressionException;
import parser.IncomparableTypeException;
import parser.ParserException;

/**
 * x++.function
 *
 * @author Alexander Broadbent
 * @version 22/03/2016
 */
public class UserFunctionUT extends FunctionTest {

    private final static String                     VAR_D               = "d";
    private final static Integer                    VAR_D_VALUE         = 5;
    private final static String                     INPUT_SQUARE_FUNC   = "func square(x) = x * x";
    private final static String                     INPUT_COMBI_FUNC    = "func times(c, d) = c * d";
    private final static String                     INPUT_BIGGER_FUNC   = "func bigger(a, b) = a > b ? a : b";
    private final static String                     INPUT_NO_ARG_FUNC   = "func noargs() = x";
    private final static String                     INPUT_BAD_NAME_FUNC = "func TRUE() = 2";
    private final static String                     INPUT_INVALID_FUNC  = "func circ(diameter) = diameter * pi /";
    private final static String                     INPUT_SQUARE_RUN    = "square(4)";
    private final static String                     INPUT_COMBI_RUN     = "times(d, 7)";
    private final static String                     INPUT_BIGGER_RUN    = "bigger(4, 7)";
    private final static String                     NAME_SQUARE_FUNC    = "square";
    private final static String                     NAME_BIGGER_FUNC    = "bigger";
    private final static String                     NAME_COMBI_FUNC     = "times";
    private final static Double                     RESULT_SQUARE_RUN   = 16d;
    private final static Integer                    RESULT_BIGGER_RUN   = 7;
    private final static Double                     RESULT_COMBI_RUN    = 35d;
    private final static Class<? extends Exception> CLASS_NO_ARG        = NullPointerException.class;
    private final static Class<? extends Exception> CLASS_BAD_NAME      = ExpressionException.class;
    private final static Class<? extends Exception> CLASS_INVALID_FUNC  = ExpressionException.class;


    @Override
    public void setUp() {
        super.setUp();

        setDomainVariable(VAR_D, VAR_D_VALUE);
    }


    @Test
    public void singleVariableFunctionTest()
            throws UnknownSequenceException, ParserException, ExpressionException, IncomparableTypeException {
        runFunctionTest(INPUT_SQUARE_FUNC, NAME_SQUARE_FUNC, INPUT_SQUARE_RUN, RESULT_SQUARE_RUN);
    }

    @Test
    public void multiVariableFunctionTest()
            throws UnknownSequenceException, ParserException, ExpressionException, IncomparableTypeException {
        runFunctionTest(INPUT_BIGGER_FUNC, NAME_BIGGER_FUNC, INPUT_BIGGER_RUN, RESULT_BIGGER_RUN);
    }

    @Test
    public void combinationVariableFunctionTest()
            throws UnknownSequenceException, ParserException, ExpressionException, IncomparableTypeException {
        runFunctionTest(INPUT_COMBI_FUNC, NAME_COMBI_FUNC, INPUT_COMBI_RUN, RESULT_COMBI_RUN);
    }

    @Test
    public void noArgumentFunctionTest() {
        runExceptionTest(INPUT_NO_ARG_FUNC, CLASS_NO_ARG);
    }

    @Test
    public void nameClashFunctionTest() {
        runExceptionTest(INPUT_BAD_NAME_FUNC, CLASS_BAD_NAME);
    }
    
    @Test
    public void invalidFunctionExceptionTest() {
        runExceptionTest(INPUT_INVALID_FUNC, CLASS_INVALID_FUNC);
    }

}
