package architecture;

import framework.LogTest;
import gui.IConstants;
import gui.XLogger;
import org.junit.After;
import org.junit.Test;

/**
 * ProgLang.architecture
 *
 * @author Alexander Broadbent
 * @version 26/03/2016
 */
public class LoggerUT extends LogTest {

    private final static String TEST_LOG     = "test log";
    private final static String TEST_WARNING = "test log";
    private final static String TEST_SEVERE  = "test log";


    @Override
    @After
    public void tearDown() {
        super.tearDown();

        // Reset the XLogger default debug mode value
        XLogger.setDebug(IConstants.DEBUG_MODE);
    }

    @Test
    public void printLogTest() {
        runLogTest(TEST_LOG);
    }

    @Test
    public void printWarningTest() {
        runWarningTest(TEST_WARNING);
    }

    @Test
    public void printSevereTest() {
        runSevereTest(TEST_SEVERE);
    }

    @Test
    public void setDebugFalseTest() {
        runDebugTest(false, TEST_LOG);
    }

    @Test
    public void setDebugTrueTest() {
        runDebugTest(true, TEST_LOG);
    }

}
