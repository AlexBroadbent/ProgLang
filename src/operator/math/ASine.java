package operator.math;

import eval.IncomparableTypeException;
import eval.Literal;
import eval.NoValueException;
import gui.XProperties;
import operator.IConstants;
import operator.IPrecedence;
import operator.base.UnaryOperator;

import static gui.IConstants.ANGLE_MODE;
import static gui.IConstants.ANGLE_MODE_DEFAULT;
import static operator.IConstants.ASIN;
import static operator.IConstants.Angle.RADIANS;

/**
 * x++.operator.common
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class ASine extends UnaryOperator {

    @Override
    public String getToken() {
        return ASIN;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.FUNCTION;
    }

    @Override
    public Object execute(Literal arg1) throws IncomparableTypeException, NoValueException {
        try {
            IConstants.Angle angle = XProperties.getAngle(ANGLE_MODE, ANGLE_MODE_DEFAULT);
            double result = Math.asin(Double.parseDouble(arg1.getValue().toString()));
            return (angle == RADIANS) ? result : Math.toDegrees(result);
        }
        catch (NumberFormatException ex) {
            throw new IncomparableTypeException(getAllowedExecutionTypes(), arg1);
        }
    }

}
