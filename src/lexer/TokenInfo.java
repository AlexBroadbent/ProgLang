package lexer;

import java.util.regex.Pattern;

/**
 * JavaCCProgrammingLanguage.parser
 *
 * @author Alexander Broadbent
 * @version 31/10/2015
 */
public class TokenInfo {

    public final Pattern regex;
    public final int     token;

    public TokenInfo(Pattern regex, int token) {
        this.regex = regex;
        this.token = token;
    }


    @Override
    public String toString() {
        return "Token: " + token + " - Regex: " + regex;
    }
}
