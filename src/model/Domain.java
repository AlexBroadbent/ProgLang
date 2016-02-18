package model;

import eval.Literal;
import eval.Variable;
import lexer.ILexer;
import lexer.Lexer;
import lexer.Token;
import operator.IOperator;
import operator.bitwise.*;
import operator.common.*;
import operator.comparator.GreaterThan;
import operator.comparator.GreaterThanEqual;
import operator.comparator.LessThan;
import operator.comparator.LessThanEqual;
import operator.conditional.Conditional;
import operator.conditional.ConditionalElse;
import operator.conditional.ConditionalEnd;
import operator.equality.Equal;
import operator.equality.NotEqual;
import operator.math.*;
import parser.IParser;
import parser.Parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LazyLanguage.model
 *
 * @version     01/12/2015
 * @author      Alexander Broadbent
 */
public class Domain {

    protected static Domain instance;

    protected ILexer lexer;
    protected IParser parser;
    protected Map<String, IOperator> operators;
    protected Map<String, Variable> variables;

    public Domain(ILexer lexer, IParser parser) {
        operators = new HashMap<>();
        variables = new HashMap<>();

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
        registerOperator(new IncrementBy());
        registerOperator(new DecrementBy());
        registerOperator(new MultiplyBy());
        registerOperator(new DivideBy());

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
        registerOperator(new ConditionalEnd());

        this.lexer = (lexer != null) ? lexer : new Lexer();
        this.parser = (parser != null) ? parser : new Parser();

        if (instance == null)
            instance = this;
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

    public boolean isOperator(String token) {
        return operators.containsKey(token);
    }

    public IOperator getOperator(String token) {
        return operators.get(token);
    }

    public boolean hasVariable(String name) {
        return variables.containsKey(name);
    }

    public Variable getVariable(String name) {
        return variables.get(name);
    }

    public void setVariables(Map<String, Variable> variables) {
        this.variables.putAll(variables);
    }

    public Map<String, Variable> getAllVariables() {
        return variables;
    }

    public Variable getOrCreateVariable(String name) {
        if (hasVariable(name))
            return getVariable(name);
        else {
            Variable var =  createVariable(name);
            variables.put(name, var);
            return var;
        }
    }

    public Variable createVariable(String name) {
        return new Variable(name);
    }


    public static Literal wrapLiteral(Object value) {
        if (value instanceof Literal)
            return (Literal) value;

        return new Literal(value);
    }

}
