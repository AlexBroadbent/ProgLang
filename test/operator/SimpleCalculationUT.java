package operator;

import framework.ExpressionTest;
import lexer.UnknownSequenceException;
import org.junit.Test;
import parser.ExpressionException;
import parser.IncomparableTypeException;
import parser.ParserException;

/**
 * x++.operator
 *
 * @author Alexander Broadbent
 * @version 29/12/2015
 */
public class SimpleCalculationUT extends ExpressionTest {

    private static final String  VAR_X       = "x";
    private static final String  VAR_Y       = "y";
    private static final Integer VAR_X_VALUE = 3;
    private static final String  VAR_Y_VALUE = "HelloWorld";

    private static final String INPUT_ADD     = "x + x";
    private static final String INPUT_SUB     = "x - x";
    private static final String INPUT_MULTI   = "x * x";
    private static final String INPUT_DIV     = "x / x";
    private static final String INPUT_CONCAT  = "y # y";
    private static final Double RESULT_ADD    = 6d;
    private static final Double RESULT_SUB    = 0d;
    private static final Double RESULT_MULTI  = 9d;
    private static final Double RESULT_DIV    = 1d;
    private static final String RESULT_CONCAT = "HelloWorldHelloWorld";


    @Override
    public void setUp() {
        super.setUp();

        setDomainVariable(VAR_X, VAR_X_VALUE);
        setDomainVariable(VAR_Y, VAR_Y_VALUE);
    }


    @Test
    public void simpleAdditionTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_ADD, RESULT_ADD);
    }

    @Test
    public void simpleSubtractionTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_SUB, RESULT_SUB);
    }

    @Test
    public void simpleMultiplicationTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_MULTI, RESULT_MULTI);
    }

    @Test
    public void simpleDivisionTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_DIV, RESULT_DIV);
    }

    @Test
    public void simpleConcatenationTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_CONCAT, RESULT_CONCAT);
    }

}
