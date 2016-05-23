package operator;

import eval.ExpressionException;
import eval.IncomparableTypeException;
import eval.NoValueException;
import framework.ExpressionTest;
import lexer.UnknownSequenceException;
import org.junit.Test;
import parser.ParserException;

/**
 * c++.operator
 *
 * @author Alexander Broadbent
 * @version 23/03/2016
 */
public class LogicalOperatorUT extends ExpressionTest {

    private static final String  VAR_W       = "w";
    private static final String  VAR_X       = "x";
    private static final String  VAR_Y       = "y";
    private static final String  VAR_Z       = "z";
    private static final Integer VAR_W_VALUE = 3;
    private static final Integer VAR_X_VALUE = 12;
    private static final Integer VAR_Y_VALUE = 6;
    private static final Boolean VAR_Z_VALUE = Boolean.TRUE;

    private static final String                     INPUT_NOT  = "!z";
    private static final String                     INPUT_AND  = "w>x && y<x";
    private static final String                     INPUT_OR   = "w>x || y<x";
    private static final String                     INPUT_ERR  = "x && z";
    private static final Boolean                    RESULT_NOT = Boolean.FALSE;
    private static final Boolean                    RESULT_AND = Boolean.FALSE;
    private static final Boolean                    RESULT_OR  = Boolean.TRUE;
    private static final Class<? extends Exception> CLASS_ERR  = IncomparableTypeException.class;


    @Override
    public void setUp() {
        super.setUp();

        setDomainVariable(VAR_W, VAR_W_VALUE);
        setDomainVariable(VAR_X, VAR_X_VALUE);
        setDomainVariable(VAR_Y, VAR_Y_VALUE);
        setDomainVariable(VAR_Z, VAR_Z_VALUE);
    }

    @Test
    public void logicalNotOperatorTest()
            throws UnknownSequenceException, ExpressionException, IncomparableTypeException, ParserException, NoValueException {
        runExpressionTest(INPUT_NOT, RESULT_NOT);
    }

    @Test
    public void logicalAndOperatorTest()
            throws UnknownSequenceException, ExpressionException, IncomparableTypeException, ParserException, NoValueException {
        runExpressionTest(INPUT_AND, RESULT_AND);
    }

    @Test
    public void logicalOrOperatorTest()
            throws UnknownSequenceException, ExpressionException, IncomparableTypeException, ParserException, NoValueException {
        runExpressionTest(INPUT_OR, RESULT_OR);
    }

    @Test
    public void incomparableLogicalAndTest() {
        runExceptionTest(INPUT_ERR, CLASS_ERR);
    }

}
