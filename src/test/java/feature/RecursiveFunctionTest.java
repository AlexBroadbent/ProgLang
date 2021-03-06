package feature;

import uk.co.alexbroadbent.eval.ExpressionException;
import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.XList;
import framework.FunctionTest;
import uk.co.alexbroadbent.lexer.UnknownSequenceException;
import org.junit.Ignore;
import org.junit.Test;
import uk.co.alexbroadbent.parser.ParserException;

/**
 * x++.feature
 *
 * @author Alexander Broadbent
 * @version 10/04/2016
 */
public class RecursiveFunctionTest extends FunctionTest {

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


    //@Test
    @Ignore( "The domain does not currently support variable storage for recursive functions" )
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
