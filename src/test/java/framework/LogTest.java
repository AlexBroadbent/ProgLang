package framework;

import uk.co.alexbroadbent.ui.IConstants;
import uk.co.alexbroadbent.ui.XLogger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * x++.framework
 *
 * @author Alexander Broadbent
 * @version 26/03/2016
 */
public abstract class LogTest extends BaseTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private PrintStream oldOutStream;
    private PrintStream oldErrStream;


    @Before
    public void setUp() {
        oldOutStream = System.out;
        oldErrStream = System.err;

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDown() {
        System.setOut(oldOutStream);
        System.setErr(oldErrStream);
    }


    protected void runLogTest(String input) {
        String expected = String.format(XLogger.getLogFormat(), input);

        XLogger.log(input);

        Assert.assertEquals(String.format(MSG_ASSERT_RESULT_LOG, getClass().getSimpleName(), expected,
                outContent.toString()), expected, outContent.toString());
    }

    protected void runWarningTest(String input) {
        String expected = String.format(XLogger.getWarningFormat(), input);

        XLogger.warning(input);

        Assert.assertEquals(String.format(MSG_ASSERT_RESULT_LOG, getClass().getSimpleName(), expected,
                errContent.toString()), expected, errContent.toString());
    }

    protected void runSevereTest(String input) {
        String expected = String.format(XLogger.getSevereFormat(), input);

        XLogger.severe(input);

        Assert.assertEquals(String.format(MSG_ASSERT_RESULT_LOG, getClass().getSimpleName(), expected,
                errContent.toString()), expected, errContent.toString());
    }

    protected void runDebugTest(Boolean debug, String input) {
        XLogger.setDebug(debug);
        String expected = debug ? String.format(XLogger.getLogFormat(), input) : "";

        XLogger.log(input);

        XLogger.setDebug(IConstants.DEBUG_MODE);
        Assert.assertEquals(String.format(MSG_ASSERT_RESULT_LOG, getClass().getSimpleName(), expected,
                outContent.toString()), expected, outContent.toString());
    }

}
