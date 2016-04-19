package type;

import eval.XList;
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
public class XListUT {

    private static final String MSG_EQUAL = "Assert list [%s] is %s equal to list [%s]";

    private static final XList w = XList.parse(1, 2, 4);
    private static final XList x = XList.parse(1, 2, 3);
    private static final XList y = XList.parse(1, 2);
    private static final XList z = XList.parse(1);

    @Test
    public void sizeTest() {
        XLogger.log(getClass().getSimpleName() + " :: " + String.format(MSG_EQUAL, x, "not", y));
        Assert.assertEquals(false, Objects.equals(x, y));
    }

    @Test
    public void typeTest() {
        XLogger.log(getClass().getSimpleName() + " :: " + String.format(MSG_EQUAL, z, "not", 1));
        Assert.assertEquals(false, Objects.equals(x, 1));
    }

    @Test
    public void elementTest() {
        XLogger.log(getClass().getSimpleName() + " :: " + String.format(MSG_EQUAL, w, "not", x));
        Assert.assertEquals(false, Objects.equals(w, x));
    }

}
