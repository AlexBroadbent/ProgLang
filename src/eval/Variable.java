package eval;

/**
 * x++.eval
 * <p>
 * Variable of a lambda infix, created using just the name, and then a value
 * is assigned later on.
 * </p>
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class Variable extends Literal {

    protected String  name;
    protected boolean valueSet;

    public Variable(String name) {
        super(null);

        this.name = name;
        valueSet = false;
    }

    public Variable(String name, Object value) {
        super(value);
        this.name = name;
        valueSet = true;
    }


    public String getName() {
        return name;
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof Literal)
            this.value = ((Literal) value).getValue();
        else
            this.value = value;

        valueSet = true;
    }

    @Override
    public int getType() {
        return ICalculableType.VARIABLE;
    }

    @Override
    public String toString() {
        return getName() + ((isValueSet()) ? " [" + getValue().toString() + "]" : "");
    }

    public boolean isValueSet() {
        return valueSet;
    }

    public String toDebugString() {
        return getName() + ((!isValueSet()) ? "" : " -> | Type: " + getValue().getClass().getSimpleName() +
                " | Value: " + getValue().toString() + " |");
    }

    public String toOutputString() {
        return isValueSet() ? getValue().toString() : "null";
    }

}
