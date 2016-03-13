package operator.function;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import eval.*;
import model.Domain;
import model.VarDomain;
import operator.Associativity;
import operator.IOperator;
import operator.IPrecedence;
import parser.ExpressionException;
import parser.IncomparableTypeException;

import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * ProgLang.operator.function
 *
 * @author Alexander Broadbent
 * @version 08/03/2016
 */
public class UserFunction extends Function {

    protected String name;
    protected List<Literal> arguments;
    protected Expression expression;
    protected VarDomain domain;


    public UserFunction(Domain model, String name, List<Literal> arguments) {
        this.name = name;
        this.arguments = arguments;
        expression = null;
        domain = new VarDomain(model);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Literal> getArguments() {
        return arguments;
    }

    public void setArguments(List<Literal> arguments) {
        this.arguments = arguments;
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

        for (int i = 0; i < arguments.size(); i++) {
            Literal arg = arguments.get(i);
            arg.setValue(args.get(i).getValue());
        }

        return expression.execute();
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
     *
     * @return function declaration is valid
     */
    public boolean validate(List<ICalculable> postfix) {
        Set<String> argNames = Sets.newHashSet();
        Set<String> expNames = Sets.newHashSet();

        for (Literal args : getArguments())
            argNames.add(((Variable) args).getName());

        for (ICalculable literal : postfix)
            if (literal.getType() == ICalculableType.VARIABLE)
                expNames.add(((Variable) literal).getName());

        return argNames.containsAll(expNames) && expNames.containsAll(argNames);
    }

}
