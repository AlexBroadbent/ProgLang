package gui;

import eval.Expression;
import eval.ICalculable;
import lexer.Lexer;
import lexer.Token;
import lexer.UnknownSequenceException;
import model.Domain;
import parser.ExpressionException;
import parser.IncomparableTypeException;
import parser.Parser;

import java.util.List;
import java.util.Scanner;

/**
 * x++.gui
 *
 * @author      Alexander Broadbent
 * @version     28/12/2015
 */
public class MainGUI {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input;
        Domain domain = new Domain(new Lexer(), new Parser());
        Expression expression;

        do {
            System.out.print("> ");
            input = scanner.nextLine();

            try {
                List<Token> tokens = domain.getLexer().readAllTokens(input);

                expression = new Expression(domain, domain.getLexer().readAllTokens(input));
                Object result = expression.execute();
                if (result != null) System.out.print("> " + result + "\n");
            }
            catch (UnknownSequenceException ex) {
                XLogger.severe("Error while lexing expression: " + ex);
            }
            catch (ExpressionException | IncomparableTypeException ex) {
                XLogger.severe("Error while executing expression: " + ex);
            }
        }
        while (!input.isEmpty());


    }

}
