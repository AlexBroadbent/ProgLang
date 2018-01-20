package uk.co.alexbroadbent.lexer;

import java.util.regex.Pattern;

/**
 * x++.parser
 *
 * @author Alexander Broadbent
 * @version 31/10/2015
 */
class TokenInfo {

    final Pattern regex;
    final int     token;

    TokenInfo(Pattern regex, int token) {
        this.regex = regex;
        this.token = token;
    }

}
