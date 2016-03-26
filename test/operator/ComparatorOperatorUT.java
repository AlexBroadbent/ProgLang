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
 * @version 24/03/2016
 */
public class ComparatorOperatorUT extends ExpressionTest {

    private static final String  VAR_X        = "x";
    private static final String  VAR_Y        = "y";
    private static final Integer VAR_X_VALUE  = 3;
    private static final Integer VAR_Y_VALUE  = 5;

    private static final String  INPUT_GT     = "x > y";
    private static final String  INPUT_GT_EQ  = "x >= x";
    private static final String  INPUT_LT     = "x < y";
    private static final String  INPUT_LT_EQ  = "x <= y";
    private static final Boolean RESULT_GT    = Boolean.FALSE;
    private static final Boolean RESULT_GT_EQ = Boolean.TRUE;
    private static final Boolean RESULT_LT    = Boolean.TRUE;
    private static final Boolean RESULT_LT_EQ = Boolean.TRUE;


    @Override
    public void setUp() {
        super.setUp();

        setDomainVariable(VAR_X, VAR_X_VALUE);
        setDomainVariable(VAR_Y, VAR_Y_VALUE);
    }


    @Test
    public void greaterThanOperatorTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_GT, RESULT_GT);
    }

    @Test
    public void greaterThanEqualOperatorTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_GT_EQ, RESULT_GT_EQ);
    }

    @Test
    public void lessThanOperatorTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_LT, RESULT_LT);
    }

    @Test
    public void lessThanEqualOperatorTest()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExpressionTest(INPUT_LT_EQ, RESULT_LT_EQ);
    }

}