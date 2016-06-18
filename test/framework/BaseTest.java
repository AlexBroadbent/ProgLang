package framework;

import model.Domain;
import org.junit.After;
import org.junit.Before;

/**
 * x++.framework
 *
 * @author Alexander Broadbent
 * @version 26/03/2016
 */
public abstract class BaseTest {

    static final String MSG_ASSERT_RESULT_LOG = "Test::%s - Asserting result [%s] equals expected [%s]";

    protected Domain domain;


    @Before
    public void setUp() {
        domain = new Domain();
    }

    @After
    public void tearDown() {
        domain = new Domain();
    }
}
