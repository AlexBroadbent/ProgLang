package gui;

import eval.Expression;
import eval.ExpressionException;
import eval.IncomparableTypeException;
import eval.NoValueException;
import lexer.UnknownSequenceException;
import model.Domain;
import operator.IConstants.Angle;
import org.apache.commons.lang3.StringUtils;
import parser.ParserException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
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
        domain = new Domain();
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


                        if (result != null && result.getClass().equals(Double.class)) {
                            int decimalPlaces = XProperties.getInteger(DECIMAL_PLACES, DECIMAL_PLACES_DEFAULT);
                            if (decimalPlaces > -1)
                                result = new BigDecimal(result.toString()).setScale(decimalPlaces, RoundingMode.HALF_UP).doubleValue();
                        }

                        if (result != null && !StringUtils.isEmpty(result.toString()))
                            System.out.println(LINE_START + result);
                    }
                    catch (ExpressionException | IncomparableTypeException | NoValueException ex) {
                        XLogger.warning(ex.getMessage());
                    }
                    catch (UnknownSequenceException | ParserException | ClassCastException ex) {
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
        input = input.replaceFirst(DOMAIN, "").trim();
        if (input.startsWith(LIST)) {
            input = input.replaceFirst(LIST, "").trim();
            if (input.startsWith(VARIABLES))
                XLogger.log("Variable list: " + domain.getAllVariables());
            if (input.startsWith(OPERATORS))
                XLogger.log("Operator list: " + domain.getOperatorList());
            if (input.startsWith(FUNCTIONS))
                XLogger.log("Function list: " + domain.getFunctionList());
            if (input.startsWith(SAVED))
                XLogger.log("Saved functions: " + domain.getSavedFunctionList());
        }
        else if (input.startsWith(FREE)) {
            input = input.replaceFirst(FREE, "").trim();
            domain.freeVariable(input);
            XLogger.log(input + " has been removed.");
        }
        else if (input.startsWith(RESET)) {
            domain = new Domain();
            XLogger.log("Domain has been reset.");
        }
        else if (input.startsWith(VARIABLE)) {
            input = input.replaceFirst(VARIABLE, "").trim();
            XLogger.log(domain.getVariable(input).toDebugString());
        }
        else if (input.startsWith(DEBUG)) {
            input = input.replaceFirst(DEBUG, "").trim();
            XLogger.setDebug(Boolean.parseBoolean(input));
            XProperties.setProperty(DEBUG_MODE, Boolean.valueOf(input).toString());
        }
        else if (input.startsWith(DECIMAL)) {
            input = input.replaceFirst(DECIMAL, "").trim();
            if (Objects.equals(input, "nolimit")) input = "-1";
            XProperties.setProperty(DECIMAL_PLACES, Integer.valueOf(input).toString());
        }
        else if (input.startsWith(ANGLE)) {
            input = input.replaceFirst(ANGLE, "").trim();
            if (input.isEmpty())
                XLogger.log("Angle Mode: " + XProperties.getAngle(ANGLE_MODE, ANGLE_MODE_DEFAULT).toString());
            else
                XProperties.setProperty(ANGLE_MODE, Angle.get(input).getValue());
        }
        else if (input.startsWith(SAVE)) {
            input = input.replaceFirst(SAVE, "").trim();
            if (Objects.equals(ALL, input))
                domain.saveAllUserFunction();
            else {
                if (domain.saveUserFunction(input))
                    XLogger.log(input + " has been saved");
                else
                    XLogger.warning("Error saving the " + input + " function");
            }
        }
        else if (input.startsWith(LOAD)) {
            input = input.replaceFirst(LOAD, "").trim();
            if (domain.loadUserFunction(input))
                XLogger.log(input + " has been loaded");
            else
                XLogger.log(input + " does not exist, or cannot be loaded");
        }
        else if (input.startsWith(VIEW)) {
            input = input.replaceFirst(VIEW, "").trim();
            XLogger.log(domain.getSavedUserFunction(input));
        }
        else {
            XLogger.log(
                    "\tcommand:   options:\n" +
                            "\tlist       variables|operators|functions|saved   list the corresponding data stored in the domain\n" +
                            "\tfree       variable_name                         delete variable from storage, allowing a new value to be assigned\n" +
                            "\tvariable   variable_name                         display the data type and value of a variable in the storage\n" +
                            "\tdebug      true|false                            set if warning and log messages are displayed\n" +
                            "\tangle      radians|degrees                       set the unit type of angles, between radians and degrees\n" +
                            "\tdecimal    0-10|nolimit                          set the number of decimal places to display\n" +
                            "\tsave       function_name|all                     save all or a named function to storage to load in the future\n" +
                            "\tload       function_name|all                     load all or a named function that had been saved from a previous\n" +
                            "\tview       function_name                         see the declaration of a function, to check if it is capable of being saved\n" +
                            "\treset                                            resets all variables, operators and functions to the program defaults"
            );
        }
    }

}
