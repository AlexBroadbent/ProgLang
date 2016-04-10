package operator;

import framework.ExpressionTest;
import lexer.UnknownSequenceException;
import org.junit.Test;
import parser.ExpressionException;
import parser.IncomparableTypeException;
import parser.ParserException;

/**
 * x++.operator
 * <p>
 * Test for lambda expressions that have multiple variables
 *
 * @author Alexander Broadbent
 * @version 30/12/2015
 */
public class MultipleVariableUT extends ExpressionTest {

    private static final String  VAR_X       = "x";
    private static final String  VAR_Y       = "y";
    private static final String  VAR_Z       = "z";
    private static final Integer VAR_X_VALUE = 3;
    private static final Integer VAR_Y_VALUE = 4;
    private static final Integer VAR_Z_VALUE = 5;

    private static final String                     INPUT     = "x + y * z";
    private static final Double                     RESULT    = 23d;
    private static final String                     INPUT_ERR = "x + y / w";
    private static final Class<? extends Exception> CLASS_ERR = ExpressionException.class;


    @Override
    public void setUp() {
        super.setUp();

        setDomainVariable(VAR_X, VAR_X_VALUE);
        setDomainVariable(VAR_Y, VAR_Y_VALUE);
        setDomainVariable(VAR_Z, VAR_Z_VALUE);
    }


    @Test
    public void simpleAdditionTest()
            throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT, RESULT);
    }

    @Test
    public void mismatchingArgumentValues() {
        runExceptionTest(INPUT_ERR, CLASS_ERR);
    }

}
