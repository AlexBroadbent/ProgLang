package feature;

import eval.XList;
import framework.BaseTest;
import gui.XLogger;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

/**
 * x++.type
 *
 * @author Alexander Broadbent
 * @version 01/12/2016
 */
public class XListTest extends BaseTest {

    private static final String MSG_EQUAL = "Test::%s - Assert list [%s] is %s equal to list [%s]";

    private static final XList w = XList.parse(1, 2, 4);
    private static final XList x = XList.parse(1, 2, 3);
    private static final XList y = XList.parse(1, 2);
    private static final XList z = XList.parse(1);


    @Test
    public void sizeTest() {
        XLogger.log(String.format(MSG_EQUAL, getClass().getSimpleName(), x, "not", y));
        Assert.assertEquals(false, Objects.equals(x, y));
    }

    @Test
    public void typeTest() {
        XLogger.log(String.format(MSG_EQUAL, getClass().getSimpleName(), z, "not", 1));
        Assert.assertEquals(false, Objects.equals(x, 1));
    }

    @Test
    public void elementTest() {
        XLogger.log(String.format(MSG_EQUAL, getClass().getSimpleName(), w, "not", x));
        Assert.assertEquals(false, Objects.equals(w, x));
    }

}
