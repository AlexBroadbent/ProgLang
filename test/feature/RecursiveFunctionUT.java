package feature;

import eval.ExpressionException;
import eval.IncomparableTypeException;
import eval.XList;
import framework.FunctionTest;
import lexer.UnknownSequenceException;
import org.junit.Test;
import parser.ParserException;

/**
 * ProgLang.feature
 *
 * @author Alexander Broadbent
 * @version 10/04/2016
 */
public class RecursiveFunctionUT extends FunctionTest {

    private final static String VAR_T       = "t";
    private final static XList  VAR_T_VALUE = XList.parse(10, 20, 30);

    private final static String DEF_FIB        = "func fib(n) = (n < 2) ? n : fib(n - 1) + fib(n - 2)";
    private final static String DEF_PROCESS    = "func process(l) = empty(l) ? 'finished' : process(tail(l))";
    private final static String RUN_FIB        = "fib(2)";
    private final static String RUN_PROCESS    = "process(t)";
    private final static String NAME_FIB       = "fib";
    private final static String NAME_PROCESS   = "process";
    private final static Double RESULT_FIB     = 1d;
    private final static String RESULT_PROCESS = "finished";


    @Override
    public void setUp() {
        super.setUp();

        setDomainVariable(VAR_T, VAR_T_VALUE);
    }


    @Test
    public void fibonacciSequenceFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runFunctionTest(DEF_FIB, NAME_FIB, RUN_FIB, RESULT_FIB);
    }

    @Test
    public void processListFunctionTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runFunctionTest(DEF_PROCESS, NAME_PROCESS, RUN_PROCESS, RESULT_PROCESS);
    }

}
