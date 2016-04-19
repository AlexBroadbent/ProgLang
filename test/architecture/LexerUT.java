package architecture;

import lexer.Lexer;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.PatternSyntaxException;

/**
 * x++.architecture
 *
 * @author Alexander Broadbent
 * @version 26/03/2016
 */
public class LexerUT {

    private final static String                     INVALID_REGEX = "@3f£F£F3r3/af33";
    private final static Class<? extends Exception> CLASS_ERR     = PatternSyntaxException.class;
    private Lexer testLexer;

    @Before
    public void setUp() {
        testLexer = new Lexer();
    }


    @Test
    public void invalidRegexToken() {

    }

}
