package syntax;

import framework.ExpressionTest;
import org.junit.Test;
import parser.ExpressionException;

/**
 * ProgLang.syntax
 *
 * @author Alexander Broadbent
 * @version 11/04/2016
 */
public class VariableDefinitionUT extends ExpressionTest {

    private final static String                     INPUT_VAL_NOT_SET = "x * 2";
    private final static Class<? extends Exception> CLASS_VAL_NOT_SET = ExpressionException.class;


    @Test
    public void variableNotSetTest() {
        runExceptionTest(INPUT_VAL_NOT_SET, CLASS_VAL_NOT_SET);
    }

}