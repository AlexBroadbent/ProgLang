package eval;

/**
 * x++.eval
 * <p>
 * Variable of a lambda infix, created using just the name, and then a value
 * is assigned later on.
 * </p>
 *
 * @author      Alexander Broadbent
 * @version     01/12/2015
 */
public class Variable extends Literal {

    protected String name;
    protected boolean valueSet;
    protected int links;            // Memory management counter -> can be deleted when links is 0

    public Variable(String name) {
        super(null);

        this.name = name;
        valueSet = false;

        links = 0;
    }

    public Variable(String name, Object value) {
        super(value);
        this.name = name;
        valueSet = true;

        links = 1;
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

    public boolean isValueSet() {
        return valueSet;
    }


    public int getLinks() {
        return links;
    }

    public void setLinks(int links) {
        this.links = links;
    }




    @Override
    public int getType() {
        return ICalculableType.VARIABLE;
    }

    @Override
    public String toString() {
        return getName() + ((isValueSet()) ? " [" + getValue().toString() + "]" : " [No Value]");
    }

    public String toDebugString() {
        return getName() + " -> Holds: " + getValue().getClass().getSimpleName() + " value: " + getValue().toString();
    }

}
