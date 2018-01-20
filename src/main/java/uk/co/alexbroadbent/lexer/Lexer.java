package uk.co.alexbroadbent.lexer;

import static uk.co.alexbroadbent.lexer.IToken.ARG_SEPARATOR;
import static uk.co.alexbroadbent.lexer.IToken.ARG_SEPARATOR_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.ARITHMETIC;
import static uk.co.alexbroadbent.lexer.IToken.ARITHMETIC_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.ARRAY_ACCESS;
import static uk.co.alexbroadbent.lexer.IToken.ARRAY_ACCESS_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.ASSIGNMENT;
import static uk.co.alexbroadbent.lexer.IToken.ASSIGNMENT_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.BINARY_8BIT;
import static uk.co.alexbroadbent.lexer.IToken.BINARY_8BIT_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.BITWISE_OPERATOR;
import static uk.co.alexbroadbent.lexer.IToken.BITWISE_OPERATOR_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.BIT_SHIFT;
import static uk.co.alexbroadbent.lexer.IToken.BIT_SHIFT_OPERATOR_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.BOOLEAN;
import static uk.co.alexbroadbent.lexer.IToken.BOOLEAN_COMPARATOR;
import static uk.co.alexbroadbent.lexer.IToken.BOOLEAN_COMPARATOR_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.BOOLEAN_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.CONDITIONAL;
import static uk.co.alexbroadbent.lexer.IToken.CONDITIONAL_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.DECIMAL;
import static uk.co.alexbroadbent.lexer.IToken.DECIMAL_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.FOR_LOOP_ELEMENT;
import static uk.co.alexbroadbent.lexer.IToken.FOR_LOOP_ELEMENT_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.FUNCTION_DECLARATION;
import static uk.co.alexbroadbent.lexer.IToken.FUNCTION_DECLARATION_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.GEOMETRIC;
import static uk.co.alexbroadbent.lexer.IToken.GEOMETRIC_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.INC_DEC;
import static uk.co.alexbroadbent.lexer.IToken.INC_DEC_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.LIST;
import static uk.co.alexbroadbent.lexer.IToken.LIST_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.LOGICAL_COMPARATOR_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.LOGICAL_OPERATOR;
import static uk.co.alexbroadbent.lexer.IToken.MATH_COMPARATOR;
import static uk.co.alexbroadbent.lexer.IToken.MATH_COMPARATOR_EQUALITY_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.MATH_COMPARATOR_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.MATH_EQUALITY_COMPARATOR;
import static uk.co.alexbroadbent.lexer.IToken.NUMBER;
import static uk.co.alexbroadbent.lexer.IToken.NUMBER_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.PARENTHESIS;
import static uk.co.alexbroadbent.lexer.IToken.PARENTHESIS_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.TEXT;
import static uk.co.alexbroadbent.lexer.IToken.TEXT_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.VARIABLE_FUNCTION;
import static uk.co.alexbroadbent.lexer.IToken.VARIABLE_OR_FUNCTION_REGEX;
import static uk.co.alexbroadbent.lexer.IToken.WHITESPACE;
import static uk.co.alexbroadbent.lexer.IToken.WHITESPACE_REGEX;

import uk.co.alexbroadbent.ui.XLogger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * x++.parser
 *
 * @author Alexander Broadbent
 * @version 31/10/2015
 */
public class Lexer implements ILexer {

    private Map<Integer, TokenInfo> tokenInfoMap;


    public Lexer() {
        tokenInfoMap = Maps.newHashMap();

        addToken(WHITESPACE_REGEX, WHITESPACE);
        addToken(BOOLEAN_COMPARATOR_REGEX, BOOLEAN_COMPARATOR);
        addToken(ASSIGNMENT_REGEX, ASSIGNMENT);
        addToken(INC_DEC_REGEX, INC_DEC);
        addToken(BINARY_8BIT_REGEX, BINARY_8BIT);
        addToken(DECIMAL_REGEX, DECIMAL);
        addToken(NUMBER_REGEX, NUMBER);
        addToken(ARITHMETIC_REGEX, ARITHMETIC);
        addToken(GEOMETRIC_REGEX, GEOMETRIC);
        addToken(BOOLEAN_REGEX, BOOLEAN);
        addToken(FUNCTION_DECLARATION_REGEX, FUNCTION_DECLARATION);
        addToken(LOGICAL_COMPARATOR_REGEX, LOGICAL_OPERATOR);
        addToken(BIT_SHIFT_OPERATOR_REGEX, BIT_SHIFT);
        addToken(MATH_COMPARATOR_EQUALITY_REGEX, MATH_EQUALITY_COMPARATOR);
        addToken(MATH_COMPARATOR_REGEX, MATH_COMPARATOR);
        addToken(BITWISE_OPERATOR_REGEX, BITWISE_OPERATOR);
        addToken(PARENTHESIS_REGEX, PARENTHESIS);
        addToken(FOR_LOOP_ELEMENT_REGEX, FOR_LOOP_ELEMENT);
        addToken(TEXT_REGEX, TEXT);
        addToken(VARIABLE_OR_FUNCTION_REGEX, VARIABLE_FUNCTION);
        addToken(CONDITIONAL_REGEX, CONDITIONAL);
        addToken(LIST_REGEX, LIST);
        addToken(ARG_SEPARATOR_REGEX, ARG_SEPARATOR);
        addToken(ARRAY_ACCESS_REGEX, ARRAY_ACCESS);
    }


    private void addToken(String regex, int token) {
        try {
            TokenInfo ti;
            if (token == BOOLEAN)
                ti = new TokenInfo(Pattern.compile("^(" + regex + ")", Pattern.CASE_INSENSITIVE), token);
            else
                ti = new TokenInfo(Pattern.compile("^(" + regex + ")"), token);

            tokenInfoMap.put(token, ti);
        }
        catch (PatternSyntaxException ex) {
            XLogger.severe("Lexer regex is invalid: " + regex + " for token " + token);
        }
    }

    @Override
    public List<Token> readAllTokens(String input) throws UnknownSequenceException {
        List<Token> tokens = Lists.newArrayList();

        while (!input.isEmpty()) {
            boolean match = false;

            for (TokenInfo info : tokenInfoMap.values()) {
                Matcher m = info.regex.matcher(input);

                if (m.find()) {
                    match = true;

                    if (info.token != WHITESPACE)
                        tokens.add(new Token(info.token, m.group().trim()));
                    input = m.replaceFirst("");

                    break;
                }
            }

            if (!match)
                throw new UnknownSequenceException(input);
        }

        return tokens;
    }

}
