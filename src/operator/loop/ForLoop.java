package operator.loop;

import com.google.common.collect.Lists;
import eval.Expression;
import eval.ICalculable;
import eval.Literal;
import eval.Variable;
import model.Domain;
import operator.IConstants;
import operator.IPrecedence;
import operator.function.Function;
import org.apache.commons.lang3.StringUtils;
import parser.ExpressionException;
import parser.IncomparableTypeException;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static eval.ICalculableType.VARIABLE;

/**
 * ProgLang.operator.loop
 *
 * @author Alexander Broadbent
 * @version 27/03/2016
 */
public class ForLoop extends Function {

    private final static String MSG_CAST         = "Cannot run the for loop with arguments: %s %s %s.";
    private final static String MSG_NUM_ARGS     = "The function requires more arguments, should be in the format: for var in list do ...expression...";
    protected Domain domain;


    @Override
    public String getToken() {
        return IConstants.FOR_LOOP;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.LOOP;
    }

    @Override
    public Literal evaluate(Domain domain, Stack<Literal> stack, boolean returnExpression)
            throws IncomparableTypeException, ExpressionException {
        this.domain = domain;
        return super.evaluate(domain, stack, returnExpression);
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public Object execute(List<Literal> args)
            throws IncomparableTypeException, ExpressionException {
        // Ensure function has all required arguments
        if (args.size() < 3)
            throw new ExpressionException(MSG_NUM_ARGS);

        // Ensure first argument is a variable type, list cannot be checked as it could be a variable or a literal and
        //  so is caught in the cast below
        if (args.get(0).getType() != VARIABLE)
            throw new IncomparableTypeException(Lists.newArrayList(Variable.class.getSimpleName()),
                    args.get(0).getValue().getClass().getSimpleName());

        // Cast variables to match and throw expression exception if anything fails
        Variable var;
        LinkedList<Literal> list;
        List<ICalculable> expr = Lists.newArrayList(args.subList(2, args.size()));
        List<Literal> results = Lists.newLinkedList();

        try {
            var = (Variable) args.get(0);
            list = (LinkedList<Literal>) args.get(1).getValue();
            expr = Expression.parseWrappedList(expr);

            // Remove do from expression, used to determine whether expression is executable previous to now
            for (int i=0; i<expr.size(); i++) {
                if (expr.get(i) instanceof Do) {
                    expr.remove(i);
                    i--;
                }
            }

            // Create an array to store the results of each iteration
            for (Literal element : list) {
                var.setValue(element.getValue());
                results.add(Domain.wrapLiteral(new Expression(expr, domain).execute()));
            }
        }
        catch (ClassCastException | NullPointerException ex) {
            String arg1 = Variable.class.getSimpleName();  // Value is not set, at this point it is known arg1 is a var
            String arg2 = (args.get(1).getValue() == null) ? "Null" : args.get(1).getValue().getClass().getSimpleName();
            String arg3 = StringUtils.join(args.subList(2, args.size()));

            throw new ExpressionException(String.format(MSG_CAST, arg1, arg2, arg3));
        }

        return results;
    }

}
