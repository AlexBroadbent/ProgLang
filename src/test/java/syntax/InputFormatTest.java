package syntax;

import uk.co.alexbroadbent.eval.ExpressionException;
import uk.co.alexbroadbent.eval.IncomparableTypeException;
import framework.ExpressionTest;
import uk.co.alexbroadbent.lexer.UnknownSequenceException;
import org.junit.Test;
import uk.co.alexbroadbent.parser.ParserException;

/**
 * x++.syntax
 *
 * @author Alexander Broadbent
 * @version 05/01/2016
 */
public class InputFormatTest extends ExpressionTest {

    private static final String  VAR_X       = "x";
    private static final String  VAR_Y       = "y";
    private static final Integer VAR_X_VALUE = 8;
    private static final Integer VAR_Y_VALUE = 4;

    private static final String                     INPUT_FORMAT_1  = "x / y + 5";
    private static final Double                     RESULT_FORMAT_1 = 7d;
    private static final String                     INPUT_FORMAT_2  = "(x / y) + 5";
    private static final Double                     RESULT_FORMAT_2 = 7d;
    private static final String                     INPUT_ERROR     = "x / y + 5 10";
    private static final Class<? extends Exception> CLASS_ERROR     = ExpressionException.class;
    private static final String                     INPUT_PAREN     = "(((x)) + (((3))))";
    private static final Double                     RESULT_PAREN    = 11d;


    @Override
    public void setUp() {
        super.setUp();

        setDomainVariable(VAR_X, VAR_X_VALUE);
        setDomainVariable(VAR_Y, VAR_Y_VALUE);
    }

    @Test
    public void testFormat1()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_FORMAT_1, RESULT_FORMAT_1);
    }

    @Test
    public void testFormat2()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_FORMAT_2, RESULT_FORMAT_2);
    }

    @Test
    public void testErrorInExecution()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExceptionTest(INPUT_ERROR, CLASS_ERROR);
    }

    @Test
    public void testUnnecessaryParenthesis()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_PAREN, RESULT_PAREN);
    }

}
