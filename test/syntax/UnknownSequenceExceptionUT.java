package syntax;

import eval.ExpressionException;
import eval.IncomparableTypeException;
import framework.ExpressionTest;
import lexer.UnknownSequenceException;
import org.junit.Test;
import parser.ParserException;

/**
 * x++.syntax
 *
 * @author Alexander Broadbent
 * @version 24/03/2016
 */
public class UnknownSequenceExceptionUT extends ExpressionTest {

    private final static String                     INPUT_UN  = "@";
    private final static String                     INPUT_VAR = "d = €";
    private final static Class<? extends Exception> CLASS_UN  = UnknownSequenceException.class;
    private final static String                     VAR_D     = "d";


    @Test
    public void unknownSymbolTest() {
        runExceptionTest(INPUT_UN, CLASS_UN);
    }

    @Test
    /*
     *  TODO: Is this expected behaviour?? A var created with bad data still exists...
     */
    public void variableCreatedWithUnknownSymbol()
            throws ExpressionException, IncomparableTypeException, ParserException, UnknownSequenceException {
        runExceptionTest(INPUT_VAR, CLASS_UN);
        hasVariableBeenCreated(VAR_D);
    }

}
