package syntax;

import eval.ExpressionException;
import eval.IncomparableTypeException;
import eval.XList;
import framework.ExpressionTest;
import lexer.UnknownSequenceException;
import org.junit.Test;
import parser.ParserException;

/**
 * x++.syntax
 *
 * @author Alexander Broadbent
 * @version 21/04/2016
 */
public class VariableNameTest extends ExpressionTest {

    private static final String                     INPUT_VAR_STARTS_WITH_FUNC      = "tails = tail(list(1, 2, 3))";
    private static final String                     INPUT_USER_FUNCTION_NAME_EXISTS = "func tail(l) = tail(l)";
    private static final String                     NAME_VAR_STARTS_WITH_FUNC       = "tails";
    private static final XList                      RESULT_VAR_STARTS_WITH_FUNC     = XList.parse(2, 3);
    private static final Class<? extends Exception> CLASS_PE                        = ParserException.class;


    @Test
    public void variableNameStartsWithFunctionNameTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runVariableTest(INPUT_VAR_STARTS_WITH_FUNC, NAME_VAR_STARTS_WITH_FUNC, RESULT_VAR_STARTS_WITH_FUNC);
    }


    @Test
    public void userFunctionWithExistingNameTest() {
        runExceptionTest(INPUT_USER_FUNCTION_NAME_EXISTS, CLASS_PE);
    }


}
