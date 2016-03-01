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
                try {
                    expression = new Expression(domain, domain.getLexer().readAllTokens(input));
                    Object result = expression.execute();

                    if (!StringUtils.isEmpty(result.toString()))
                        System.out.print("> " + result + "\n");
                }
                catch (UnknownSequenceException | ExpressionException | IncomparableTypeException ex) {
                    XLogger.severe(ex.getMessage());
                }
            }

            System.out.print("> ");
            input = scanner.nextLine();
        }

    }

}
