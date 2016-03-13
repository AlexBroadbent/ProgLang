package eval;

/**
 * ProgLang.eval
 *
 * @author Alexander Broadbent
 * @version 12/03/2016
 */
public class FunctionPlaceholder extends Literal {


    public FunctionPlaceholder() {
        super(null);
    }


    @Override
    public int getType() {
        return ICalculableType.FUNCTION_PLACEHOLDER;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
