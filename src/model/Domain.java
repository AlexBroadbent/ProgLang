package model;

import com.google.common.collect.Maps;
import eval.*;
import gui.XLogger;
import lexer.ILexer;
import lexer.Lexer;
import lexer.UnknownSequenceException;
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
import operator.list.*;
import operator.loop.Do;
import operator.loop.ForLoop;
import operator.loop.In;
import operator.math.*;
import org.apache.commons.lang3.StringUtils;
import parser.IParser;
import parser.Parser;
import parser.ParserException;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static eval.ICalculableType.USER_FUNCTION;

/**
 * x++.model
 * <p>
 * A single class that is the centre of the language; stores all operators, functions, variables and has
 * an associated lexer and parser.
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class Domain implements Cloneable {

    private final static String FILE_SAVED_FUNCTION = "saved_func.sav";
    private final static String MSG_NOT_FOUND       = "There is no function called %s in the saved function file.";

    private static Path savedFunctionsPath = null;
    protected ILexer                             lexer;
    protected IParser                            parser;
    private   Map<String, IOperator>             operators;
    private   Map<String, IFunction>             functions;
    private   Map<String, Variable>              variables;
    private   Map<String, Map<String, Variable>> functionalVariables;
    private   Map<String, String>                savedFunctions;


    public Domain() {
        this(null, null);
    }

    public Domain(ILexer lexer, IParser parser) {
        // Initialise the maps used for functions, operators and variables
        operators = Maps.newHashMap();
        functions = Maps.newHashMap();
        variables = Maps.newHashMap();
        functionalVariables = Maps.newHashMap();
        savedFunctions = Maps.newHashMap();

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

        // set stored function file
        if (savedFunctionsPath == null) {
            try {
                savedFunctionsPath = Paths.get(new File(FILE_SAVED_FUNCTION).toURI());
                loadSavedFunctions();
            }
            catch (IllegalArgumentException | FileSystemNotFoundException | SecurityException e) {
                XLogger.severe("Cannot load savedFunctionsPath function file: " + e.getMessage());
            }
        }
    }

    public static Literal wrapLiteral(Object value) {
        if (value instanceof Literal)
            return (Literal) value;

        return new Literal(value);
    }

    @Override
    public Domain clone() {
        try {
            return (Domain) super.clone();
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
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
     *      Helper functions for the user to interact with through the MainGUI
     */

    public boolean freeVariable(String name) {
        return hasVariable(name) && variables.remove(name) != null;
    }

    public Collection<Variable> getAllVariables() {
        return variables.values();
    }

    public String getOperatorList() {
        return StringUtils.join(operators.values(), " ");
    }

    public String getFunctionList() {
        return StringUtils.join(functions.values(), " ");
    }

    public String getSavedFunctionList() {
        return StringUtils.join(savedFunctions.keySet(), " ");
    }

    public String getSavedUserFunction(String name) {
        if (functions.get(name).getType() != USER_FUNCTION)
            return null;

        return ((UserFunction) functions.get(name)).getDeclaration();
    }

    public boolean saveUserFunction(String name) {
        if (functions.get(name) != null) {
            IFunction function = functions.get(name);

            return function.getType() == ICalculableType.USER_FUNCTION;


        }

        return false;
    }

    public boolean saveAllUserFunction() {
        return true;
    }

    public boolean loadUserFunction(String name) {
        if (savedFunctions.containsKey(name)) {
            String declaration = savedFunctions.get(name);

            try {
                Expression expression = new Expression(this, getLexer().readAllTokens(declaration));
                expression.execute();
                return true;
            }
            catch (ExpressionException | UnknownSequenceException | ParserException | IncomparableTypeException | NoValueException e) {
                XLogger.severe(e.getMessage());
                return false;
            }
        }

        XLogger.severe(String.format(MSG_NOT_FOUND, name));
        return false;
    }

    public boolean loadAllUserFunctions() {
        for (String name : savedFunctions.keySet())
            if (!loadUserFunction(name))
                return false;
        return true;
    }

    private void loadSavedFunctions() {
        try {
            java.util.List<String> declarations = Files.readAllLines(savedFunctionsPath);

            for (int i = 0; i < declarations.size(); i++) {
                String[] d = declarations.get(i).split(";");

                if (d.length != 2)
                    XLogger.warning("Error in saved function, on line " + i + ". Format should be name;declaration");
                else
                    savedFunctions.put(d[0], d[1]);
            }
        }
        catch (IOException e) {
            XLogger.severe("Error loading function from file: " + e.getMessage());
        }
    }
}
