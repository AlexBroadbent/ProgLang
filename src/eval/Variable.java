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
    private   boolean valueSet;

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
    public Object getValue() throws NoValueException {
        if (!valueSet)
            throw new NoValueException(getName());
        return super.getValue();
    }

    @Override
    public void setValue(Object value) {
        super.setValue(value);
        valueSet = true;
    }

    @Override
    public int getType() {
        return ICalculableType.VARIABLE;
    }

    @Override
    public String toString() {
        try {
            return getName() + ((isValueSet()) ? " [" + getValue().toString() + "]" : "");
        }
        catch (NoValueException ex) {
            return getName();
        }
    }

    public boolean isValueSet() {
        return valueSet;
    }

    public String toDebugString() {
        try {
            return getName() + ((!isValueSet()) ? "" : " -> | Type: " + getValue().getClass().getSimpleName() +
                    " | Value: " + getValue().toString() + " |");
        }
        catch (NoValueException ex) {
            return getName() + " -> Value: null";
        }
    }

}
