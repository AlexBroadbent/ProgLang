package eval;

import com.google.common.collect.Maps;
import parser.ExpressionException;
import parser.IncomparableTypeException;

import java.util.Map;

/**
 * LazyLanguage.eval
 *
 *  Emacs Lisp format:
 *      (lambda (arg) (calc) (nums)) eg. (lambda (x) (* x x) (2)) = 2x2 = 4
 *      calls:
 *          &optional in arg means arg may or may not be given
 *          &rest in arg means any number of arguments, like ... in Java
 *
 *
 *  Lambda notation:
 *      (\y.4*y) ((\z.z^2 + 2 * z)5)
 *
 *
 * @author      Alexander Broadbent
 * @version     01/12/2015
 */
public class LambdaExpression {

    protected Map<String, Variable> argVariables;
    protected Map<Integer, String> argPositions;
    protected Expression body;
    protected String infix;


    public LambdaExpression(String expression)  {
        this.infix = expression;
        argVariables = Maps.newHashMap();
        argPositions = Maps.newHashMap();
    }


    public void addArg(Variable arg, int position) {
        argVariables.put(arg.getName(), arg);
        argPositions.put(position, arg.getName());
    }

    public void setArgValue(int position, Object value) {
        argVariables.get(argPositions.get(position)).setValue(value);
    }

    public Map<String, Variable> getVariables() {
        return argVariables;
    }

    public Object getVariableValue(String name) {
        return argVariables.get(name).getValue();
    }

    public void setBody(Expression bodyExpression) {
        body = bodyExpression;
    }

    public Object execute() throws ExpressionException, IncomparableTypeException {
        return body.execute();
    }

    public boolean validate() {
        return body.validate();
    }

    public boolean validateArgs() {
        for (Variable variable : argVariables.values())
            if (!variable.valueSet)
                return false;
        return true;
    }

    @Override
    public String toString() {
        return infix;
    }

}
