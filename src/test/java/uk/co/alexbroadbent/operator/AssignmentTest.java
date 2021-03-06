package uk.co.alexbroadbent.operator;

import uk.co.alexbroadbent.eval.ExpressionException;
import uk.co.alexbroadbent.eval.IncomparableTypeException;
import framework.ExpressionTest;
import uk.co.alexbroadbent.lexer.UnknownSequenceException;
import org.junit.Before;
import org.junit.Test;
import uk.co.alexbroadbent.parser.ParserException;

/**
 * x++.operator
 *
 * @author Alexander Broadbent
 * @version 30/12/2015
 */
public class AssignmentTest extends ExpressionTest {

    private static final String  VAR_X       = "x";
    private static final String  VAR_Y       = "y";
    private static final Integer VAR_X_VALUE = 4;
    private static final Integer VAR_Y_VALUE = 5;

    private static final String                     INPUT_ASS  = "z = x + y";
    private static final String                     VAR_Z      = "z";
    private static final String                     INPUT_IMM  = "x = 3";
    private static final String                     INPUT_NON  = "4 = 3";
    private static final Double                     RESULT_ASS = 9d;
    private static final Class<? extends Exception> CLASS_IMM  = ExpressionException.class;
    private static final Class<? extends Exception> CLASS_NON  = ExpressionException.class;


    @Before
    public void setUp() {
        super.setUp();

        setDomainVariable(VAR_X, VAR_X_VALUE);
        setDomainVariable(VAR_Y, VAR_Y_VALUE);
    }


    @Test
    public void standardAssignmentTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runVariableTest(INPUT_ASS, VAR_Z, RESULT_ASS);
    }

    @Test
    public void immutableAssignmentTest() throws ExpressionException, UnknownSequenceException {
        runExceptionTest(INPUT_IMM, CLASS_IMM);
    }

    @Test
    public void nonVariableAssignmentTest() {
        runExceptionTest(INPUT_NON, CLASS_NON);
    }

}
