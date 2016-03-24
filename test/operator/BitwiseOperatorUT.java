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
 * @version 30/12/2015
 */
public class BitwiseOperatorUT extends ExpressionTest {

    private static final String VAR_X = "x";
    private static final String VAR_Y = "y";
    private static final String VAR_X_BINARY = "binaryX";
    private static final String VAR_Y_BINARY = "binaryY";
    private static final Integer VAR_X_VAL = 5;
    private static final Integer VAR_Y_VAL = 3;
    private static final Integer VAR_X_VAL_BINARY = Integer.parseUnsignedInt("00011010", 2); // Same method as Literal parser
    private static final Integer VAR_Y_VAL_BINARY = 2;

    // Verified against: http://www.miniwebtool.com/bitwise-calculator/
    private static final String INPUT_NOT = "~x";
    private static final String INPUT_AND = "x & y";
    private static final String INPUT_OR = "x | y";
    private static final String INPUT_XOR = "x $ y";
    private static final Integer RESULT_NOT = -6;
    private static final Integer RESULT_AND = 1;
    private static final Integer RESULT_OR = 7;
    private static final Integer RESULT_XOR = 6;

    // Verified against: http://www.miniwebtool.com/bitwise-calculator/bit-shift/
    private static final String INPUT_LBS = "binaryX << binaryY";
    private static final String INPUT_RBS = "binaryX >> binaryY";
    private static final Integer RESULT_LBS = 104;
    private static final Integer RESULT_RBS = 6;


    @Override
    public void setUp() {
        super.setUp();

        setDomainVariable(VAR_X, VAR_X_VAL);
        setDomainVariable(VAR_Y, VAR_Y_VAL);
        setDomainVariable(VAR_X_BINARY, VAR_X_VAL_BINARY);
        setDomainVariable(VAR_Y_BINARY, VAR_Y_VAL_BINARY);
    }

    @Test
    public void bitwiseNot() throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_NOT, RESULT_NOT);
    }

    @Test
    public void bitwiseAnd() throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_AND, RESULT_AND);
    }

    @Test
    public void bitwiseOr() throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_OR, RESULT_OR);
    }

    @Test
    public void bitwiseXor() throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_XOR, RESULT_XOR);
    }

    @Test
    public void bitwiseLeftShift() throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_LBS, RESULT_LBS);
    }

    @Test
    public void bitwiseRightShift() throws ExpressionException, UnknownSequenceException, IncomparableTypeException, ParserException {
        runExpressionTest(INPUT_RBS, RESULT_RBS);
    }

}
