package gui;

import eval.Expression;
import eval.Literal;
import lexer.UnknownSequenceException;
import model.Domain;
import org.apache.commons.lang3.StringUtils;
import parser.ExpressionException;
import parser.IncomparableTypeException;
import parser.ParserException;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static gui.IConstants.*;

/**
 * x++.gui
 *
 * @author Alexander Broadbent
 * @version 28/12/2015
 */
public class MainGUI {

    protected static Domain domain;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        domain = Domain.getInstance();
        Expression expression;

        System.out.print(LOGGER_NAME + " " + LOGGER_VERSION + "\n" + LINE_START);
        input = scanner.nextLine();

        while (!input.equalsIgnoreCase(EXIT)) {
            if (!input.isEmpty()) {
                if (input.startsWith(DOMAIN))
                    handleDomainQueries(input);
                else {
                    try {
                        expression = new Expression(domain, domain.getLexer().readAllTokens(input));
                        Object result = expression.execute();

                        if (result != null && !StringUtils.isEmpty(result.toString()))
                            System.out.println(LINE_START + ((result instanceof List) ? parseList(result) : result));
                    }
                    catch (UnknownSequenceException | ParserException | ClassCastException ex) {
                        XLogger.warning(ex.getMessage());
                    }
                    catch (ExpressionException | IncomparableTypeException ex) {
                        XLogger.severe(ex.getMessage());
                    }
                }
            }

            System.out.print(LINE_START);
            input = scanner.nextLine();
        }
    }

    /**
     * Handle user requests to display language requests; such as variables, operators, functions.
     *
     * @param input user input
     */
    private static void handleDomainQueries(String input) {
        input = input.replaceFirst(DOMAIN + SPACE, "");
        if (input.startsWith(LIST)) {
            input = input.replaceFirst(LIST + SPACE, "");
            if (input.startsWith(VARIABLES))
                XLogger.log("Variable list: " + domain.getAllVariables());
            if (input.startsWith(OPERATORS))
                XLogger.log("Operator list: " + domain.getOperatorList());
            if (input.startsWith(FUNCTIONS))
                XLogger.log("Function list: " + domain.getFunctionList());
        }
        else if (input.startsWith(FREE)) {
            input = input.replaceFirst(FREE + SPACE, "");
            domain.freeVariable(input);
            XLogger.log(input + " has been removed.");
        }
        else if (input.startsWith(RESET)) {
            Domain.resetInstance();
            domain = Domain.getInstance();
            XLogger.log("Domain has been reset.");
        }
        else if (input.startsWith(VARIABLE)) {
            input = input.replaceFirst(VARIABLE + SPACE, "");
            XLogger.log(domain.getVariable(input).toDebugString());
        }
        else if (input.startsWith(DEBUG)) {
            input = input.replaceFirst(DEBUG + SPACE, "");
            XLogger.setDebug(Boolean.parseBoolean(input));
        }
        else {
            XLogger.log("The following commands are available:\n" +
                    "\tlist       variables|operators|functions   list the corresponding data stored in the domain\n" +
                    "\tfree       variable_name                   delete variable from storage, allowing a new value to be assigned\n" +
                    "\tvariable   variable_name                   display the data type and value of a variable in the storage\n" +
                    "\tdebug      true|false                      set if warning and log messages are displayed\n" +
                    "\treset                                      resets all variables, operators and functions to the program defaults");
        }
    }


    @SuppressWarnings( "unchecked" ) // ClassCastException is thrown
    private static String parseList(Object result) throws ClassCastException {
        String out = "";

        LinkedList<Literal> list = (LinkedList<Literal>) result;
        for (Literal literal : list) {
            out += literal;
            if (list.indexOf(literal) != list.size() - 1) out += " -> ";
        }

        return out;
    }

}
