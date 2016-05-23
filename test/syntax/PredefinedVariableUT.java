package syntax;

import framework.ExpressionTest;
import org.junit.Test;

/**
 * x++.operator
 * <p>
 * <pre>
 *  Ensure that the predefined variables are correctly initialised when
 *      calculating a value.
 *
 *  The predefined variables are:
 *  - pi
 *  - e
 * </pre>
 *
 * @author Alexander Broadbent
 * @version 04/01/2016
 */
public class PredefinedVariableUT extends ExpressionTest {

    private static final String  VAR_X       = "x";
    private static final Integer VAR_X_VALUE = 1;

    private static final String INPUT_PI  = "sin(x / pi)";
    private static final Double RESULT_PI = Math.sin(1 / Math.PI);
    private static final String INPUT_E   = "e^0.1";
    private static final Double RESULT_E  = Math.exp(0.1);


    @Override
    public void setUp() {
        super.setUp();

        setDomainVariable(VAR_X, VAR_X_VALUE);
    }

    @Test
    public void variablePiTest() throws Exception {
        runExpressionTest(INPUT_PI, RESULT_PI);
    }

    @Test
    public void variableETest() throws Exception {
        runExpressionTest(INPUT_E, RESULT_E);
    }

}
