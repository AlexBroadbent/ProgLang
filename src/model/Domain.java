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
import operator.list.ListEnd;
import operator.list.ListStart;
import operator.math.*;
import org.apache.commons.lang3.StringUtils;
import parser.IParser;
import parser.Parser;

import java.util.Map;
import java.util.Set;

/**
 * LazyLanguage.model
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class Domain {

    protected static Domain instance = null;
    protected ILexer lexer;
    protected IParser parser;
    protected Map<String, IOperator> operators;
    protected Map<String, IFunction> functions;
    protected Map<String, Variable> variables;
    public Domain(ILexer lexer, IParser parser) {
        // Initialise the maps used for functions, operators and variables
        operators = Maps.newHashMap();
        functions = Maps.newHashMap();
        variables = Maps.newHashMap();

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
        registerOperator(new ArgSeparator());

        // bitwise package operators
        registerOperator(new Not());
        registerOperator(new And());
        registerOperator(new Or());
        registerOperator(new Xor());
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
        registerOperator(new ListStart());
        registerOperator(new ListEnd());

        // function package functions
        registerFunction(new Sum());
        registerFunction(new Max());
        registerFunction(new List());
        registerFunction(new Head());
        registerFunction(new Tail());
        registerFunction(new Cons());
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

    public static void invalidateInstance() {
        instance = null;
    }

    public ILexer getLexer() {
        return lexer;
    }

    public IParser getParser() {
        return parser;
    }

    public void registerOperator(IOperator operator) {
        operators.put(operator.getToken(), operator);
    }

    public void registerFunction(IFunction function) {
        lexer.addUserFunctionName(function.getToken()); // Register the function with the lexer
        functions.put(function.getToken(), function);
    }

    public boolean isOperator(String token) {
        return operators.containsKey(token);
    }

    public boolean isFunction(String token) {
        return functions.containsKey(token);
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
        return variables.get(name);
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

    public Variable createVariable(String name) {
        return new Variable(name);
    }

    public void freeVariable(String name) {
        if (hasVariable(name))
            variables.remove(name);
    }

    public int getVariableCount() {
        return variables.size();
    }

    public Set<Map.Entry<String, Variable>> getAllVariables() {
        return variables.entrySet();
    }

    public String getOperatorList() {
        return StringUtils.join(operators.entrySet(), ", ");
    }

    public String getFunctionList() {
        return StringUtils.join(functions.entrySet(), ", ");
    }

}
