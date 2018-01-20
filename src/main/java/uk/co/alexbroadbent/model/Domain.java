package uk.co.alexbroadbent.model;

import static uk.co.alexbroadbent.eval.ICalculableType.USER_FUNCTION;

import uk.co.alexbroadbent.eval.Literal;
import uk.co.alexbroadbent.eval.Variable;
import uk.co.alexbroadbent.lexer.ILexer;
import uk.co.alexbroadbent.lexer.Lexer;
import uk.co.alexbroadbent.operator.IFunction;
import uk.co.alexbroadbent.operator.IOperator;
import uk.co.alexbroadbent.operator.bitwise.And;
import uk.co.alexbroadbent.operator.bitwise.LeftShift;
import uk.co.alexbroadbent.operator.bitwise.Not;
import uk.co.alexbroadbent.operator.bitwise.Or;
import uk.co.alexbroadbent.operator.bitwise.RightShift;
import uk.co.alexbroadbent.operator.bitwise.XOr;
import uk.co.alexbroadbent.operator.common.Add;
import uk.co.alexbroadbent.operator.common.ArgSeparator;
import uk.co.alexbroadbent.operator.common.Assignment;
import uk.co.alexbroadbent.operator.common.Concat;
import uk.co.alexbroadbent.operator.common.Decrement;
import uk.co.alexbroadbent.operator.common.Divide;
import uk.co.alexbroadbent.operator.common.Increment;
import uk.co.alexbroadbent.operator.common.LeftParenthesis;
import uk.co.alexbroadbent.operator.common.LogicalAnd;
import uk.co.alexbroadbent.operator.common.LogicalNot;
import uk.co.alexbroadbent.operator.common.LogicalOr;
import uk.co.alexbroadbent.operator.common.Multiply;
import uk.co.alexbroadbent.operator.common.RightParenthesis;
import uk.co.alexbroadbent.operator.common.Subtract;
import uk.co.alexbroadbent.operator.comparator.GreaterThan;
import uk.co.alexbroadbent.operator.comparator.GreaterThanEqual;
import uk.co.alexbroadbent.operator.comparator.LessThan;
import uk.co.alexbroadbent.operator.comparator.LessThanEqual;
import uk.co.alexbroadbent.operator.conditional.Conditional;
import uk.co.alexbroadbent.operator.conditional.ConditionalElse;
import uk.co.alexbroadbent.operator.equality.Equal;
import uk.co.alexbroadbent.operator.equality.NotEqual;
import uk.co.alexbroadbent.operator.function.List;
import uk.co.alexbroadbent.operator.function.Max;
import uk.co.alexbroadbent.operator.function.Random;
import uk.co.alexbroadbent.operator.function.Round;
import uk.co.alexbroadbent.operator.function.Sum;
import uk.co.alexbroadbent.operator.list.ArrayAccessEnd;
import uk.co.alexbroadbent.operator.list.ArrayAccessStart;
import uk.co.alexbroadbent.operator.list.Cons;
import uk.co.alexbroadbent.operator.list.Empty;
import uk.co.alexbroadbent.operator.list.Head;
import uk.co.alexbroadbent.operator.list.ListEnd;
import uk.co.alexbroadbent.operator.list.ListStart;
import uk.co.alexbroadbent.operator.list.Size;
import uk.co.alexbroadbent.operator.list.Tail;
import uk.co.alexbroadbent.operator.loop.Do;
import uk.co.alexbroadbent.operator.loop.ForLoop;
import uk.co.alexbroadbent.operator.loop.In;
import uk.co.alexbroadbent.operator.math.ACosine;
import uk.co.alexbroadbent.operator.math.ASine;
import uk.co.alexbroadbent.operator.math.ATangent;
import uk.co.alexbroadbent.operator.math.Cosine;
import uk.co.alexbroadbent.operator.math.Exponential;
import uk.co.alexbroadbent.operator.math.Log10;
import uk.co.alexbroadbent.operator.math.Mod;
import uk.co.alexbroadbent.operator.math.NaturalLog;
import uk.co.alexbroadbent.operator.math.Power;
import uk.co.alexbroadbent.operator.math.Sine;
import uk.co.alexbroadbent.operator.math.SquareRoot;
import uk.co.alexbroadbent.operator.math.Tangent;
import uk.co.alexbroadbent.parser.IParser;
import uk.co.alexbroadbent.parser.Parser;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * x++.model
 * <p>
 * A single class that is the centre of the language; stores all operators, functions, variables and has
 * an associated lexer and parser.
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
        registerOperator(new Add());
        registerOperator(new ArgSeparator());
        registerOperator(new Assignment());
        registerOperator(new Concat());
        registerOperator(new Decrement());
        registerOperator(new Divide());
        registerOperator(new Increment());
        registerOperator(new LeftParenthesis());
        registerOperator(new LogicalAnd());
        registerOperator(new LogicalNot());
        registerOperator(new LogicalOr());
        registerOperator(new Multiply());
        registerOperator(new RightParenthesis());
        registerOperator(new Subtract());

        // bitwise package operators
        registerOperator(new And());
        registerOperator(new LeftShift());
        registerOperator(new Not());
        registerOperator(new Or());
        registerOperator(new RightShift());
        registerOperator(new XOr());

        // math package operators
        registerOperator(new ACosine());
        registerOperator(new ASine());
        registerOperator(new ATangent());
        registerOperator(new Cosine());
        registerOperator(new Exponential());
        registerOperator(new Log10());
        registerOperator(new Mod());
        registerOperator(new NaturalLog());
        registerOperator(new Power());
        registerOperator(new Sine());
        registerOperator(new SquareRoot());
        registerOperator(new Tangent());

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
        registerOperator(new ArrayAccessEnd());
        registerOperator(new ArrayAccessStart());
        registerOperator(new ListEnd());
        registerOperator(new ListStart());

        // loop package operators
        registerFunction(new ForLoop());
        registerOperator(new Do());
        registerOperator(new In());

        // function package functions
        registerFunction(new List());
        registerFunction(new Max());
        registerFunction(new Random());
        registerFunction(new Round());
        registerFunction(new Sum());

        // list package functions
        registerFunction(new Cons());
        registerFunction(new Empty());
        registerFunction(new Head());
        registerFunction(new Size());
        registerFunction(new Tail());

        // set singleton instance
        if (instance == null)
            instance = this;
    }


    public static Domain getInstance() {
        if (instance == null)
            instance = new Domain();

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
        functions.put(function.getToken(), function);
        if (function.getType() == USER_FUNCTION)
            functionalVariables.put(function.getToken(), Maps.newHashMap());
    }

    public boolean isOperator(String operatorName) {
        return operators.containsKey(operatorName);
    }

    public boolean isFunction(String functionName) {
        return functions.containsKey(functionName);
    }

    private boolean hasFunctionalVariable(String functionName, String variableName) {
        return functionalVariables.get(functionName).containsKey(variableName);
    }

    public Variable addFunctionalVariable(String functionName, String variableName) {
        if (!hasFunctionalVariable(functionName, variableName))
            functionalVariables.get(functionName).put(variableName, createVariable(variableName));

        return functionalVariables.get(functionName).get(variableName);
    }

    public Variable getFunctionalVariable(String functionName, String variableName) {
        if (hasFunctionalVariable(functionName, variableName))
            return functionalVariables.get(functionName).get(variableName);

        return getVariable(variableName);
    }

    public IOperator getOperator(String operatorName) {
        return operators.get(operatorName);
    }

    public IFunction getFunction(String functionName) {
        return functions.get(functionName);
    }

    public boolean hasVariable(String variableName) {
        return variables.containsKey(variableName);
    }

    public Variable getVariable(String variableName) {
        if (hasVariable(variableName))
            return variables.get(variableName);

        return getOrCreateVariable(variableName);
    }

    public Variable getOrCreateVariable(String variableName) {
        if (hasVariable(variableName))
            return getVariable(variableName);
        else {
            Variable var = createVariable(variableName);
            variables.put(variableName, var);
            return var;
        }
    }

    private Variable createVariable(String variableName) {
        return new Variable(variableName);
    }

    public Set<String> getVariableNames() {
        return variables.keySet();
    }


    /*
     *      Helper functions for the user to interact with through the MainUI
     */

    public void freeVariable(String name) {
        if (hasVariable(name))
            variables.remove(name);
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
