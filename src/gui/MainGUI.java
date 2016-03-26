package gui;

import eval.Expression;
import lexer.UnknownSequenceException;
import model.Domain;
import org.apache.commons.lang3.StringUtils;
import parser.ExpressionException;
import parser.IncomparableTypeException;
import parser.ParserException;

import java.util.Scanner;

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

        while (!input.equals("exit")) {
            if (!input.isEmpty()) {
                if (input.startsWith("domain")) {
                    handleDomainQueries(input);
                }
                else {
                    try {
                        expression = new Expression(domain, domain.getLexer().readAllTokens(input));
                        Object result = expression.execute();

                        if (result != null && !StringUtils.isEmpty(result.toString()))
                            System.out.print("> " + result + "\n");
                    }
                    catch (UnknownSequenceException | ExpressionException | IncomparableTypeException | ParserException ex) {
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
    protected static void handleDomainQueries(String input) {
        input = input.replaceFirst("domain ", "");
        if (input.startsWith("size"))
            XLogger.log("Domain size is " + domain.getVariableCount());
        else if (input.startsWith("list")) {
            input = input.replaceFirst("list ", "");
            if (input.startsWith("variables"))
                XLogger.log("Variable list: " + domain.getAllVariables());
            if (input.startsWith("operators"))
                XLogger.log("Operator list: " + domain.getOperatorList());
            if (input.startsWith("functions"))
                XLogger.log("Function list: " + domain.getFunctionList());
        }
        else if (input.startsWith("free")) {
            input = input.replaceFirst("free ", "");
            domain.freeVariable(input);
            XLogger.log(input + " has been removed.");
        }
        else if (input.startsWith("invalidate")) {
            Domain.invalidateInstance();
            domain = Domain.getInstance();
            XLogger.log("Domain has been invalidated.");
        }
        else if (input.startsWith("var")) {
            input = input.replaceFirst("var ", "");
            XLogger.log(domain.getVariable(input).toDebugString());
        }
        else if (input.startsWith("debug")) {
            input = input.replaceFirst("debug ", "");
            XLogger.setDebug(Boolean.parseBoolean(input));
        }
    }

}
