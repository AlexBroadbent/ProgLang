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

import static eval.ICalculableType.LITERAL;
import static eval.ICalculableType.VARIABLE;

/**
 * ProgLang.operator.loop
 *
 * @author Alexander Broadbent
 * @version 27/03/2016
 */
public class ListLoop extends Function {

    private final static String MSG_INVALID_VAR  = "The first argument of the for loop should be a new variable, instead found %s.";
    private final static String MSG_INVALID_LIST = "The second argument of the for loop should be a list to iterate, instead found %s.";
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
    public List<String> getAllowedExecutionTypes() {
        return Lists.newArrayList(Variable.class.getSimpleName(), LinkedList.class.getSimpleName(),
                Expression.class.getSimpleName());
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

        // Ensure first argument is a variable type
        if (args.get(0).getType() != VARIABLE)
            throw new ExpressionException(String.format(MSG_INVALID_VAR, args.get(0).getValue().getClass().getSimpleName()));
        // Ensure second argument is a variable or list
        if (!(args.get(1).getType() == LITERAL || args.get(1).getType() == VARIABLE))
            throw new ExpressionException(String.format(MSG_INVALID_LIST, args.get(1).getValue().getClass().getSimpleName()));

        // Cast variables to match and throw expression exception if anything fails
        Variable var;
        LinkedList<Literal> list;
        List<ICalculable> expr = Lists.newArrayList(args.subList(2, args.size()));
        try {
            var = (Variable) args.get(0);
            list = (LinkedList<Literal>) args.get(1).getValue();
            expr = Expression.parseWrappedList(expr);
        }
        catch (ClassCastException | NullPointerException ex) {
            String arg1 = args.get(0).getValue().getClass().getSimpleName();
            String arg2 = args.get(1).getValue().getClass().getSimpleName();
            String arg3 = StringUtils.join(args.subList(2, args.size()));

            throw new ExpressionException(String.format(MSG_CAST, arg1, arg2, arg3));
        }

        // Create an array to store the results of each iteration
        List<Literal> results = Lists.newArrayList();
        for (Literal element : list) {
            var.setValue(element.getValue());
            results.add(Domain.wrapLiteral(new Expression(expr, domain).execute()));
        }

        return results;
    }
}
