package uk.co.alexbroadbent.operator.function;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import eval.*;
import uk.co.alexbroadbent.model.Domain;
import uk.co.alexbroadbent.operator.Associativity;
import uk.co.alexbroadbent.operator.IPrecedence;
import uk.co.alexbroadbent.operator.IUserFunction;
import uk.co.alexbroadbent.operator.loop.Do;
import uk.co.alexbroadbent.operator.loop.ForLoop;
import org.apache.commons.lang3.StringUtils;
import uk.co.alexbroadbent.eval.Expression;
import uk.co.alexbroadbent.eval.ExpressionException;
import uk.co.alexbroadbent.eval.ICalculable;
import uk.co.alexbroadbent.eval.ICalculableType;
import uk.co.alexbroadbent.eval.IncomparableTypeException;
import uk.co.alexbroadbent.eval.Literal;
import uk.co.alexbroadbent.eval.Variable;

import java.util.List;
import java.util.Set;

/**
 * x++.operator.function
 *
 * @author Alexander Broadbent
 * @version 08/03/2016
 */
public class UserFunction extends Function implements IUserFunction {

    protected String         name;
    protected List<Variable> arguments;
    protected Expression expression;
    protected Domain         model;


    public UserFunction(Domain model, String name, List<Variable> arguments) {
        this.model = model;
        this.name = name;
        this.arguments = arguments;
        expression = null;
    }

    public UserFunction(Domain model, String name) {
        this(model, name, Lists.newArrayList());
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addArgument(Variable arg) {
        arguments.add(arg);
    }

    @Override
    public List<Variable> getArguments() {
        return arguments;
    }

    @Override
    public void setArguments(List<Variable> arguments) {
        this.arguments = arguments;
    }

    @Override
    public Expression getExpression() {
        return expression;
    }

    @Override
    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * Match the variables used with those in the expression
     * <p>
     * Variable defined in the domain can be used in expressions, eg. pi, and so
     * only the variables used in the arguments of the function have to be used
     * in the expression; not vice-versa.
     *
     * @return function declaration is valid
     */
    @Override
    public boolean validate() throws ExpressionException {
        Set<String> argNames = Sets.newHashSet();
        Set<String> expNames = Sets.newHashSet();
        for (Variable arg : getArguments())
            argNames.add(arg.getName());

        boolean inForLoop = false;
        boolean forLoop = false;
        for (ICalculable literal : expression.getExpression())
            if (literal instanceof ForLoop)
                inForLoop = forLoop = true;
            else if (literal instanceof Do)
                inForLoop = false;
            else if (literal.getType() == ICalculableType.VARIABLE && !inForLoop)
                expNames.add(((Variable) literal).getName());

        // Check if all arguments are used in the expression
        if (expNames.containsAll(argNames) && argNames.containsAll(expNames))
            return true;

        // Check if arguments exist in the domain, the value can be null because
        // for loops have a variable which is not set yet.
        expNames.removeAll(argNames);
        if (forLoop)
            return model.getVariableNames().containsAll(expNames);

        for (Variable variable : model.getAllVariables())
            if (!variable.isValueSet())
                return false;
        return true;
    }

    @Override
    public String getToken() {
        return getName();
    }

    @Override
    public int getNumOperands() {
        return arguments.size();
    }

    @Override
    public Associativity getAssociativity() {
        return Associativity.LEFT_TO_RIGHT;
    }

    @Override
    public int getPrecedence() {
        return IPrecedence.FUNCTION;
    }

    @Override
    public List<String> getAllowedExecutionTypes() {
        return Lists.newArrayList(Literal.class.getSimpleName());
    }

    @Override
    public int getType() {
        return ICalculableType.USER_FUNCTION;
    }

    @Override
    public Object execute(List<Literal> args) throws IncomparableTypeException, ExpressionException {
        if (args.size() != arguments.size())
            throw new ExpressionException(String.format(MSG_SIZE_ARG, arguments.size(), args.size()));

        // set values for the functional variables
        for (int i = 0; i < arguments.size(); i++) {
            Variable var = model.getFunctionalVariable(name, (arguments.get(i)).getName());
            var.setValue(args.get(i));
        }

        return getExpression().execute();
    }

    @Override
    public String toString() {
        List<String> vars = Lists.newArrayList();
        for (Variable arg : getArguments())
            vars.add(arg.getName());

        return getName() + ((!vars.isEmpty()) ? "(" + StringUtils.join(vars, ", ") + ")" : "");
    }

}
