package gui;

import eval.Expression;
import eval.Literal;
import lexer.UnknownSequenceException;
import model.Domain;
import org.apache.commons.lang3.StringUtils;
import parser.ExpressionException;
import parser.IncomparableTypeException;
import parser.ParserException;

import java.util.ArrayList;
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

        System.out.print(IConstants.LOGGER_NAME + " " + IConstants.LOGGER_VERSION + "\n> ");
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
                            System.out.print("> " + ((result instanceof List) ? parseList(result) : result) + "\n");
                    }
                    catch (UnknownSequenceException | ExpressionException | IncomparableTypeException | ParserException | ClassCastException ex) {
                        XLogger.warning(ex.getMessage());
                    }
                }
            }

            System.out.print("> ");
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
        if (input.startsWith("size"))
            XLogger.log("Domain size is " + domain.getVariableCount());
        else if (input.startsWith(LIST)) {
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
        else if (input.startsWith(INVALIDATE)) {
            Domain.invalidateInstance();
            domain = Domain.getInstance();
            XLogger.log("Domain has been invalidated.");
        }
        else if (input.startsWith(VARIABLE)) {
            input = input.replaceFirst(VARIABLE + SPACE, "");
            XLogger.log(domain.getVariable(input).toDebugString());
        }
        else if (input.startsWith(DEBUG)) {
            input = input.replaceFirst(DEBUG + SPACE, "");
            XLogger.setDebug(Boolean.parseBoolean(input));
        }
    }


    @SuppressWarnings( "unchecked" ) // ClassCastException is thrown
    private static String parseList(Object result) throws ClassCastException {
        String out = "";

        if (result instanceof LinkedList) {
            LinkedList<Literal> list = (LinkedList<Literal>) result;
            for (Literal literal : list) {
                out += literal; if (list.indexOf(literal) != list.size()-1) out += " -> ";
            }
        }
        if (result instanceof ArrayList) {
            ArrayList<Literal> list = (ArrayList<Literal>) result;
            for (Literal literal : list) {
                out += "\t"+literal.getValue(); if (list.indexOf(literal) != list.size()-1) out += "\n";
            }
        }

        return out;
    }

}
