package operator.function;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import eval.*;
import model.Domain;
import operator.Associativity;
import operator.IOperator;
import operator.IPrecedence;
import org.apache.commons.lang3.StringUtils;
import parser.ExpressionException;
import parser.IncomparableTypeException;

import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * x++.operator.function
 *
 * @author Alexander Broadbent
 * @version 08/03/2016
 */
public class UserFunction extends Function {

    protected String name;
    protected List<Literal> arguments;
    protected Expression expression;
    protected Domain model;


    public UserFunction(Domain model, String name, List<Literal> arguments) {
        this.model = model;
        this.name = name;
        this.arguments = arguments;
        expression = null;
    }


    public String getName() {
        return name;
    }

    public List<Literal> getArguments() {
        return arguments;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Object execute(List<Literal> args) throws IncomparableTypeException, ExpressionException {
        if (args.size() != arguments.size())
            throw new ExpressionException("Not enough arguments for function, expected " + arguments.size() + " and received " + args.size());

        // set values for the functional variables
        for (int i = 0; i < arguments.size(); i++) {
            Variable var = model.getFunctionalVariable(name, ((Variable) arguments.get(i)).getName());
            var.setValue(args.get(i));
        }

        return getExpression().execute();
    }

    @Override
    public int getType() {
        return ICalculableType.USER_FUNCTION;
    }

    @Override
    public String getToken() {
        return name;
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
    public boolean isValidContext(Stack<IOperator> operatorStack, List<ICalculable> infix, int position) {
        return true;
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
    public boolean validate(List<ICalculable> postfix, Domain domain) throws InvalidFunctionException {
        Set<String> argNames = Sets.newHashSet();
        Set<String> expNames = Sets.newHashSet();
        for (Literal args : getArguments())
            argNames.add(((Variable) args).getName());
        for (ICalculable literal : postfix)
            if (literal.getType() == ICalculableType.VARIABLE)
                expNames.add(((Variable) literal).getName());

        // Check if all arguments are used in the expression
        if (expNames.containsAll(argNames))
            return true;

        // Check the variables from the domain
        argNames.removeAll(expNames);
        for (String varName : argNames)
            if (domain.hasVariable(varName)) {
                if (!domain.getVariable(varName).isValueSet())
                    throw new InvalidFunctionException("Expression contains variable (" + varName + ") that does not have a value set.");
            }
            else
                throw new InvalidFunctionException("The variable, " + varName + ", has not been declared.");

        return true;
    }


    @Override
    public String toString() {
        List<Variable> vars = Lists.newArrayList();
        for (Literal arg : getArguments())
            vars.add((Variable) arg);

        return getName() + ((!vars.isEmpty()) ? "(" + StringUtils.join(vars, ", ") + ")" : "");
    }
}
