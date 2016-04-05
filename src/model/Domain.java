package model;

import com.google.common.collect.Maps;
import eval.Literal;
import eval.Variable;
import lexer.ILexer;
import lexer.Lexer;
import operator.IFunction;
import operator.IOperator;
import operator.bitwise.*;
import operator.common.*;
import operator.comparator.GreaterThan;
import operator.comparator.GreaterThanEqual;
import operator.comparator.LessThan;
import operator.comparator.LessThanEqual;
import operator.conditional.Conditional;
import operator.conditional.ConditionalElse;
import operator.equality.Equal;
import operator.equality.NotEqual;
import operator.function.*;
import operator.list.ArrayAccessEnd;
import operator.list.ArrayAccessStart;
import operator.list.ListEnd;
import operator.list.ListStart;
import operator.loop.Do;
import operator.loop.ForLoop;
import operator.loop.In;
import operator.math.*;
import org.apache.commons.lang3.StringUtils;
import parser.IParser;
import parser.Parser;

import java.util.Collection;
import java.util.Map;

/**
 * x++.model
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class Domain {

    private static Domain instance = null;
    protected ILexer                             lexer;
    protected IParser                            parser;
    private   Map<String, IOperator>             operators;
    private   Map<String, IFunction>             functions;
    private   Map<String, Variable>              variables;
    private   Map<String, Map<String, Variable>> functionalVariables;


    public Domain() {
        this(null, null);
    }

    public Domain(ILexer lexer, IParser parser) {
        // Initialise the maps used for functions, operators and variables
        operators = Maps.newHashMap();
        functions = Maps.newHashMap();
        variables = Maps.newHashMap();
        functionalVariables = Maps.newHashMap();

        // Create lexer and parser if necessary
        this.lexer = (lexer != null) ? lexer : new Lexer();
        this.parser = (parser != null) ? parser : new Parser();

        // Add predefined variables
        variables.put("pi", new Variable("pi", Math.PI));
        variables.put("e", new Variable("e", Math.E));

        // common package operators
        registerOperator(new Assignment());
        registerOperator(new LeftParenthesis());
        registerOperator(new RightParenthesis());
        registerOperator(new Add());
        registerOperator(new Subtract());
        registerOperator(new Multiply());
        registerOperator(new Divide());
        registerOperator(new Increment());
        registerOperator(new Decrement());
        registerOperator(new LogicalAnd());
        registerOperator(new LogicalOr());
        registerOperator(new LogicalNot());
        registerOperator(new ArgSeparator());
        registerOperator(new Concat());

        // bitwise package operators
        registerOperator(new Not());
        registerOperator(new And());
        registerOperator(new Or());
        registerOperator(new XOr());
        registerOperator(new LeftShift());
        registerOperator(new RightShift());

        // math package operators
        registerOperator(new Power());
        registerOperator(new SquareRoot());
        registerOperator(new Exponential());
        registerOperator(new NaturalLog());
        registerOperator(new Mod());
        registerOperator(new Log10());
        registerOperator(new Sine());
        registerOperator(new Cosine());
        registerOperator(new Tangent());
        registerOperator(new ASine());
        registerOperator(new ACosine());
        registerOperator(new ATangent());

        // comparator package
        registerOperator(new GreaterThan());
        registerOperator(new GreaterThanEqual());
        registerOperator(new LessThan());
        registerOperator(new LessThanEqual());

        // equality package operators
        registerOperator(new Equal());
        registerOperator(new NotEqual());

        // conditional package operators
        registerOperator(new Conditional());
        registerOperator(new ConditionalElse());

        // list package operators
        registerOperator(new ArrayAccessStart());
        registerOperator(new ArrayAccessEnd());
        registerOperator(new ListStart());
        registerOperator(new ListEnd());

        // loop package operators
        registerOperator(new Do());
        registerOperator(new In());
        registerOperator(new ForLoop());

        // function package functions
        registerFunction(new Sum());
        registerFunction(new Max());
        registerFunction(new List());
        registerFunction(new Head());
        registerFunction(new Tail());
        registerFunction(new Cons());
        registerFunction(new Empty());
        registerFunction(new Declaration());

        // set singleton instance
        if (instance == null)
            instance = this;
    }

    public static Domain getInstance() {
        if (instance == null)
            instance = new Domain(null, null);

        return instance;
    }

    public static Literal wrapLiteral(Object value) {
        if (value instanceof Literal)
            return (Literal) value;

        return new Literal(value);
    }

    public static void resetInstance() {
        instance = null;
    }

    public ILexer getLexer() {
        return lexer;
    }

    public IParser getParser() {
        return parser;
    }

    private void registerOperator(IOperator operator) {
        operators.put(operator.getToken(), operator);
    }

    public void registerFunction(IFunction function) {
        lexer.addUserFunctionName(function.getToken());         // Register the function with the lexer
        functions.put(function.getToken(), function);
    }

    public boolean isOperator(String token) {
        return operators.containsKey(token);
    }

    public boolean isFunction(String token) {
        return functions.containsKey(token);
    }

    private boolean hasFunctionalVariable(String function, String variable) {
        return functionalVariables.get(function).containsKey(variable);
    }

    public Variable getFunctionalVariable(String function, String variable) {
        if (hasFunctionalVariable(function, variable))
            return functionalVariables.get(function).get(variable);

        return getVariable(variable);
    }

    public Variable addFunctionalVariable(String function, String name) {
        // empty map check
        if (!isFunction(function) && functionalVariables.get(function) == null)
            functionalVariables.put(function, Maps.newHashMap());

        // duplication check
        if (!hasFunctionalVariable(function, name))
            functionalVariables.get(function).put(name, createVariable(name));

        return functionalVariables.get(function).get(name);
    }

    public IOperator getOperator(String token) {
        return operators.get(token);
    }

    public IFunction getFunction(String token) {
        return functions.get(token);
    }

    public boolean hasVariable(String name) {
        return variables.containsKey(name);
    }

    public Variable getVariable(String name) {
        if (hasVariable(name))
            return variables.get(name);

        return getOrCreateVariable(name);
    }

    public Variable getOrCreateVariable(String name) {
        if (hasVariable(name))
            return getVariable(name);
        else {
            Variable var = createVariable(name);
            variables.put(name, var);
            return var;
        }
    }

    private Variable createVariable(String name) {
        return new Variable(name);
    }






    /*
     *      Functions for the GUI to interact with
     */

    public void freeVariable(String name) {
        if (hasVariable(name))
            variables.remove(name);
    }

    public int getVariableCount() {
        return variables.size();
    }

    public Collection<Variable> getAllVariables() {
        return variables.values();
    }

    public String getOperatorList() {
        return StringUtils.join(operators.values(), ", ");
    }

    public String getFunctionList() {
        return StringUtils.join(functions.values(), ", ");
    }

}
