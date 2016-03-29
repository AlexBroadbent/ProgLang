package operator.loop;

import operator.IConstants;
import operator.IPrecedence;
import operator.base.NullaryOperator;

/**
 * ProgLang.operator.loop
 *
 * @author Alexander Broadbent
 * @version 27/03/2016
 */
public class In extends NullaryOperator {

    @Override
    public String getToken() {
        return IConstants.IN;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.LOOP;
    }

}
