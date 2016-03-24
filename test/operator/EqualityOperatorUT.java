package operator;

import framework.ExpressionTest;
import lexer.UnknownSequenceException;
import org.junit.Test;
import parser.ExpressionException;
import parser.IncomparableTypeException;
import parser.ParserException;

/**
 * c++.operator
 *
 * @author Alexander Broadbent
 * @version 23/03/2016
 */
public class EqualityOperatorUT extends ExpressionTest {

    private static final String VAR_W = "w";
    private static final String VAR_X = "x";
    private static final String VAR_Y = "y";
    private static final String VAR_Z = "z";
    private static final Integer VAR_W_VALUE = 3;
    private static final Integer VAR_X_VALUE = 12;
    private static final Integer VAR_Y_VALUE = 6;
    private static final Boolean VAR_Z_VALUE = Boolean.TRUE;

    private static final String INPUT_EQ = "x == y";
    private static final String INPUT_EQ_2 = "x > w == z";
    private static final String INPUT_NTEQ = "x != y";
    private static final String INPUT_NTEQ_2 = "x > w != z";
    private static final Boolean RESULT_EQ = Boolean.FALSE;
    private static final Boolean RESULT_EQ_2 = Boolean.TRUE;
    private static final Boolean RESULT_NTEQ = Boolean.TRUE;
    private static final Boolean RESULT_NTEQ_2 = Boolean.FALSE;


    @Override
    public void setUp() {
        super.setUp();

        setDomainVariable(VAR_W, VAR_W_VALUE);
        setDomainVariable(VAR_X, VAR_X_VALUE);
        setDomainVariable(VAR_Y, VAR_Y_VALUE);
        setDomainVariable(VAR_Z, VAR_Z_VALUE);
    }

    @Test
    public void equalOperatorTest() throws UnknownSequenceException, ExpressionException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_EQ, RESULT_EQ);
    }

    @Test
    public void equalOperator2Test() throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_EQ_2, RESULT_EQ_2);
    }

    @Test
    public void notEqualOperatorTest() throws UnknownSequenceException, ExpressionException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_NTEQ, RESULT_NTEQ);
    }

    @Test
    public void notEqualOperator2Test() throws UnknownSequenceException, ExpressionException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_NTEQ_2, RESULT_NTEQ_2);
    }

}
