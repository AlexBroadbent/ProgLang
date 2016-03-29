package lexer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import gui.XLogger;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static lexer.IToken.*;

/**
 * JavaCCProgrammingLanguage.parser
 *
 * @author Alexander Broadbent
 * @version 31/10/2015
 */
public class Lexer implements ILexer {

    private Map<Integer, TokenInfo> tokenInfoMap;
    private List<String>            userFunctionNameList;


    public Lexer() {
        tokenInfoMap = Maps.newHashMap();
        userFunctionNameList = Lists.newArrayList();

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
        addToken(getUserFunctionNameRegex(), FUNCTION);
        addToken(FUNCTION_DECLARATION_REGEX, FUNCTION_DECLARATION);
        addToken(LOGICAL_COMPARATOR_REGEX, LOGICAL_OPERATOR);
        addToken(BIT_SHIFT_OPERATOR_REGEX, BIT_SHIFT);
        addToken(MATH_COMPARATOR_EQUALITY_REGEX, MATH_EQUALITY_COMPARATOR);
        addToken(MATH_COMPARATOR_REGEX, MATH_COMPARATOR);
        addToken(BITWISE_OPERATOR_REGEX, BITWISE_OPERATOR);
        addToken(LEFT_PAREN_REGEX, LEFT_PARENTHESIS);
        addToken(RIGHT_PAREN_REGEX, RIGHT_PARENTHESIS);
        addToken(FOR_LOOP_ELEMENT_REGEX, FOR_LOOP_ELEMENT);
        addToken(TEXT_REGEX, TEXT);
        addToken(VARIABLE_REGEX, VARIABLE);
        addToken(IF_REGEX, IF);
        addToken(ELSE_REGEX, ELSE);
        addToken(LIST_START_REGEX, LIST_START);
        addToken(LIST_END_REGEX, LIST_END);
        addToken(ARG_SEPARATOR_REGEX, ARG_SEPARATOR);
        addToken(ARRAY_ACCESS_REGEX, ARRAY_ACCESS);
    }


    private void addToken(String regex, Integer token) {
        try {
            TokenInfo ti = new TokenInfo(Pattern.compile("^(" + regex + ")"), token);
            tokenInfoMap.put(token, ti);
        }
        catch (PatternSyntaxException ex) {
            XLogger.severe("Lexer regex is invalid: " + regex + " for token " + token);
        }
    }

    private String getUserFunctionNameRegex() {
        return FUNCTION_REGEX.concat("|" + StringUtils.join(userFunctionNameList, "|"));
    }

    @Override
    public List<Token> readAllTokens(String input) throws UnknownSequenceException {
        return readAllTokens(input, false);
    }

    public List<Token> readAllTokens(String input, boolean ignoreList) throws UnknownSequenceException {
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

    /**
     * Add a user function to the lexer
     *
     * @param name the name of the function to add to the lexer
     */
    @Override
    public void addUserFunctionName(String name) {
        userFunctionNameList.add(name);
        tokenInfoMap.put(FUNCTION, new TokenInfo(Pattern.compile("^(" + getUserFunctionNameRegex() + ")"), FUNCTION));
    }

}
