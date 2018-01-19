package eval;

/**
 * x++.eval
 *
 * @author Alexander Broadbent
 * @version 07/04/2016
 */
public class ConditionalPlaceholder extends Literal {

    public ConditionalPlaceholder() {
        super(null);
    }


    @Override
    public int getType() {
        return ICalculableType.CONDITIONAL_PLACEHOLDER;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
