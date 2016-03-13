package gui;

import eval.Expression;
import lexer.Lexer;
import lexer.UnknownSequenceException;
import model.Domain;
import org.apache.commons.lang3.StringUtils;
import parser.ExpressionException;
import parser.IncomparableTypeException;
import parser.Parser;

import java.util.Scanner;

/**
 * x++.gui
 *
 * @author Alexander Broadbent
 * @version 28/12/2015
 */
public class MainGUI {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input;
        Domain domain = new Domain(new Lexer(), new Parser());
        Expression expression;

        System.out.print("> ");
        input = scanner.nextLine();

        while (!input.equals("exit")) {
            if (!input.isEmpty()) {
                if (input.startsWith("domain")) {
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
                }
                else {
                    try {
                        expression = new Expression(domain, domain.getLexer().readAllTokens(input));
                        Object result = expression.execute();

                        if (result != null && !StringUtils.isEmpty(result.toString()))
                            System.out.print("> " + result + "\n");
                    }
                    catch (UnknownSequenceException | ExpressionException | IncomparableTypeException ex) {

                        XLogger.severe(ex.getMessage());
                    }
                }
            }

            System.out.print("> ");
            input = scanner.nextLine();
        }

    }

}
